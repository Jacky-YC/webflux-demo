package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.repo.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;

	@Qualifier("")
	private final ProductService productService;

	@Override
	@Transactional
	public Mono<Order> save(Order order) {
		return repository.save(order);
	}

	@Override
	@Transactional
	public Mono<Void> delete(Order order) {
		return repository.delete(order);
	}

	@Override
	@Transactional
	public Mono<Order> update(Order order) {
		return repository.save(order);
	}

	@Override
	@Transactional
	public Mono<Order> patch(Order order) {
		return repository.save(order);
	}

	@Override
	public Flux<Order> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Order> findById(Long id) {
		return repository.findById(id);
	}

	/**
	 * 下订单
	 *
	 * @param productId 产品id
	 * @param number    产品数量
	 * @return 订单
	 */
	@Override
	public synchronized void placeOrder(Long productId, Long number) {
		// 1. 查库存
		productService.findById(productId).subscribe(product -> toPlaceOrder(product, number));
	}


	private Lock lock = new ReentrantLock();

	@Transactional
	synchronized Mono<Void> toPlaceOrder(Product product, Long number) {
		// 2. 库存充足，扣减库存
		subtractStock(product, number).subscribe(result -> {
			// 根据减库存的结果下单
			if (result) {
				// 2.1 下订单
				Order order = new Order("库存充足，下单成功！");
				order.setNumber(number);
				order.setProductId(product.getId());
				save(order).subscribe(savedOrder -> log.info("下单成功! 订单信息: {}", savedOrder));
			}
		});
		return Mono.empty();
	}

	/**
	 * 减库存
	 *
	 * @param product
	 * @param number
	 * @return
	 */
	@Transactional
	synchronized Mono<Boolean> subtractStock(Product product, Long number) {
		if (product.getStock() > 0 && product.getStock() >= number) {
			long stock = product.getStock() - number;
			product.setStock(stock);
			productService.patch(product).subscribe(product1 -> log.info("patch {} stock: {}", product1.getName(), stock));
			return Mono.just(true);
		} else {
			log.warn("productId: {}, name: {}, 库存不足，无法下单，请补充库存!", product.getId(), product.getName());
			return Mono.just(false);
		}
	}
}
