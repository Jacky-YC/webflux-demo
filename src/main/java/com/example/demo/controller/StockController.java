package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/stocks")
@RequiredArgsConstructor
public class StockController {


	private final RedissonClient redisson;

	private final StringRedisTemplate stringRedisTemplate;

	private final static String PRODUCT_NAME = "phone";

	private final static String DISTRIBUTED_LOCK_NAME = "d_lock";

	@GetMapping("/deduction")
	public String deduction() {
		RLock lock = redisson.getLock(DISTRIBUTED_LOCK_NAME);
		try {
			lock.lock();
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(PRODUCT_NAME));
			if (stock > 0) {
				Integer newStock = stock - 1;
				stringRedisTemplate.opsForValue().set(PRODUCT_NAME, newStock.toString());
				System.out.println("扣减库存成功，剩余库存: " + newStock);
				return "扣减库存成功，剩余库存: " + newStock;
			} else {
				System.out.println("剩余库存不足");
				return "剩余库存不足";
			}
		} finally {
			lock.unlock();
		}
	}

}
