package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v0/stocks")
@RequiredArgsConstructor
public class StockController {


	private final StringRedisTemplate stringRedisTemplate;

	private final static String PRODUCT_NAME = "phone";

	/**
	 * 分布式锁的名称
	 */
	private final static String LOCK_NAME = "d_lock";

	@GetMapping("/deduction")
	public String deduction() {
		// lock id
		String uuid = UUID.randomUUID().toString();
		// 1. 获取 redis 锁
		Boolean lockState = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_NAME, uuid);
		if (!lockState) {
			return "手速太快，服务器跟不上了...";
		}

		// 业务逻辑
		try {
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(PRODUCT_NAME));
			if (stock > 0) {
				Integer newStock = stock - 1;
				stringRedisTemplate.opsForValue().set(PRODUCT_NAME, newStock.toString());
				System.out.println("扣减库存成功，剩余库存: " + newStock);
			} else {
				System.out.println("扣减库存失败，剩余库存不足～");
			}
		}

		// 2.  释放锁
		finally {
			stringRedisTemplate.delete(LOCK_NAME);
		}
		return "end";
	}

}
