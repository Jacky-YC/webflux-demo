package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.domain.Product;
import com.example.demo.repo.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;

	private final ProductService productService;

	@Override
	public Mono<Order> save(Order order) {
		return repository.save(order);
	}

	@Override
	public void delete(Order order) {
		repository.delete(order).subscribe();
	}

	@Override
	public void update(Order order) {
		repository.save(order).subscribe();
	}

	@Override
	public void patch(Order order) {
		repository.save(order).subscribe();
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
	public void placeOrder(Long productId, Long number) {
		// 1. 查库存
		productService.findById(productId).subscribe(product -> toPlaceOrder(product, number));
	}

	void toPlaceOrder(Product product, Long number) {
		if (product.getStock() >= number) {
			// 2. 库存充足，扣减库存
			product.setStock(product.getStock() - number);
			productService.patch(product);

			// 2.1 下订单
			Order order = new Order("库存充足，下单成功！");
			order.setNumber(number);
			order.setProductId(product.getId());
			save(order).subscribe(savedOrder -> System.out.println("下单成功! 订单信息: " + savedOrder));
		}
	}
}
