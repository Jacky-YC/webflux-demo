package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/stocks")
@RequiredArgsConstructor
public class StockController {


	private final StringRedisTemplate stringRedisTemplate;

	private final static String PRODUCT_NAME = "phone";

	@GetMapping("/deduction")
	public String deduction() {
		// 1. 获取 redis 锁
		synchronized (this) {
			Integer stock = Integer.valueOf(stringRedisTemplate.opsForValue().get(PRODUCT_NAME));
			if (stock > 0) {
				Integer newStock = stock - 1;
				stringRedisTemplate.opsForValue().set(PRODUCT_NAME, newStock.toString());
				System.out.println("扣减库存成功，剩余库存: " + newStock);
			} else {
				System.out.println("扣减库存失败，剩余库存不足～");
			}
		}
		// 2.  释放锁
		return "end";
	}

}
