package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequestMapping("/v0/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/list")
	public Flux<Order> list() {
		return orderService.findAll();
	}

	@PostMapping("/placeOrder")
	public Flux placeOrder(@RequestParam Long productId, @RequestParam Long number) {
		orderService.placeOrder(productId, number);
		return Flux.empty();
	}

}
