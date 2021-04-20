package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RequestMapping("/v0/order")
@RestController
@RequiredArgsConstructor
public class OrderController {


	private final StringRedisTemplate redisTemplate;

	private final OrderService orderService;

	@GetMapping("/list")
	public Flux<Order> list() {
		return orderService.findAll();
	}

	@PostMapping("/placeOrder")
	public Flux placeOrder(@RequestParam Long productId, @RequestParam Long number) {
		System.out.println(new Random().nextInt());
		orderService.placeOrder(productId, number);
		return Flux.empty();
	}
	// JVM 级别的锁
	// synchronized

	private static final String STOCK_NAME = "stock";

	private static final String DISTRIBUTION_LOCK = "jacky";

	private CountDownLatch countDownLatch = new CountDownLatch(3);

	@SneakyThrows
	@GetMapping("/placeOrder2")
	public Mono<String> placeOrder2() {
		while (!tryLock()) {
			TimeUnit.SECONDS.sleep(1);
			countDownLatch.countDown();
			System.out.println(Thread.currentThread().getName() + " try lock! " + (3 - countDownLatch.getCount()) + " 次");
		}
		// 加锁成功
		try {
			// 扣减redis中的库存
			String value = redisTemplate.opsForValue().get(STOCK_NAME);
			int stock = Integer.parseInt(value);
			int stocked = --stock;
			redisTemplate.opsForValue().set(STOCK_NAME, stocked + "");
			System.out.println("扣减库存成功， 剩余库存：" + stocked);
		} finally {
			redisTemplate.delete(DISTRIBUTION_LOCK);
		}
		return Mono.just("success");
	}

	private Boolean tryLock() {
		Boolean lockState = redisTemplate.opsForValue().setIfAbsent(DISTRIBUTION_LOCK, UUID.randomUUID().toString());
		return lockState;
	}

}
