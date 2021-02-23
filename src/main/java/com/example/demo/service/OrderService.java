package com.example.demo.service;


import com.example.demo.domain.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

	Mono<Order> save(Order order);

	void delete(Order order);

	void update(Order order);

	void patch(Order order);

	Flux<Order> findAll();

	Mono<Order> findById(Long id);

	void placeOrder(Long productId, Long number);
}
