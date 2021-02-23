package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.repo.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository repository;

	private final ProductService productService;

	@Override
	public Order save(Order order) {
		return repository.save(order);
	}

	@Override
	public void delete(Order order) {
		repository.delete(order);
	}

	@Override
	public void update(Order order) {
		repository.save(order);
	}

	@Override
	public void patch(Order order) {
		repository.save(order);
	}

	@Override
	public List<Order> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Order> findById(Long id) {
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
	public Optional<Order> placeOrder(Long productId, Long number) {
		// 1. 查库存
		productService.findById(productId).ifPresentOrElse(product -> {
			if (product.getStock() > number) {
				// 2. 库存充足，扣减库存
				Order order = new Order("库存充足，下单成功！");
				order.setNumber(number);
				order.setProduct(product);
				// 2.1 下订单
				Order savedOrder = save(order);
			} else {
				// 3. 库存不足，返回
				Order order = new Order("库存不足，无法为您下单！");
			}
		}, () -> {
			new Order("查不到此商品！");
		});


		return Optional.empty();
	}
}
