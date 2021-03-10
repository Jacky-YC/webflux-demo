package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	private final Environment env;

	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();

		String host = env.getProperty("spring.redis.host");
		String port = env.getProperty("spring.redis.port");
		String address = "redis://" + host + ":" + port;

		System.out.println(address);
		config.useSingleServer()
				.setAddress(address);
		return Redisson.create(config);
	}

}
