package com.example.demo.service;


import com.example.demo.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

	Order save(Order order);

	void delete(Order order);

	void update(Order order);

	void patch(Order order);

	List<Order> findAll();

	Optional<Order> findById(Long id);

	Optional<Order> placeOrder(Long productId, Long number);
}
