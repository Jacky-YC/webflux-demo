package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v0/stocks")
@RequiredArgsConstructor
public class StockController {


	private final RedissonClient redisson;

	private final StringRedisTemplate stringRedisTemplate;

	private final static String PRODUCT_NAME = "phone";

	private final static String DISTRIBUTED_LOCK_NAME = "d_lock";

	private final static String MY_LOCK_NAME = "my_d_lock";

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

	@SneakyThrows
	@GetMapping("/deduction2")
	public String deduction2() {
		// setnx 获取锁
		String uuid = UUID.randomUUID().toString();
		// 超时时间： 死锁， 原子性
		Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(MY_LOCK_NAME, uuid, 30, TimeUnit.SECONDS);
		if (!lock) {
			// 自旋等待
			while (true) {
				Boolean zLock = stringRedisTemplate.opsForValue().setIfAbsent(MY_LOCK_NAME, uuid);
				if (zLock) {
					break;
				}
				// 使线程休眠，释放cpu资源30s
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				System.out.println("获取锁失败~");
			}
			System.out.println("获取锁成功~");
		}
		try {
			int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get(PRODUCT_NAME));

			// 场景1
			Thread.sleep(5000);
			int i = 1 / 0;

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
			// 保证线程只能释放自己的加的锁，而不能释放其他线程加的锁
			String lockValue = stringRedisTemplate.opsForValue().get(MY_LOCK_NAME);
			if (null != lockValue && lockValue.equals(uuid)) {
				stringRedisTemplate.delete(MY_LOCK_NAME);
			}
		}
	}

}
