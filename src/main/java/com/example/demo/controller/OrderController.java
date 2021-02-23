package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/v0/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/list")
	public void list() {
		orderService.findAll();
	}

	@PostMapping("/placeOrder")
	public ResponseEntity<Order> placeOrder(Long productId, Long number) {
		Optional<Order> optionalOrder = orderService.placeOrder(productId, number);
		if (optionalOrder.isPresent()) {
			return ResponseEntity.ok(optionalOrder.get());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Order("产品库存不足!"));
		}
	}

}
