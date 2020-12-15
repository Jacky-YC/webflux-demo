package com.example.demo.service;

import com.example.demo.domain.Stu;
import com.example.demo.repo.StuRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:18
 */
@Service
@RequiredArgsConstructor
public class StuService {

	private final StuRepo repo;

	@Transactional(rollbackFor = DataAccessException.class)
	public Mono<Stu> saveOrUpdate(Stu stu) {
		return repo.save(stu);
	}

	public Mono findById(Long id) {
		return repo.findById(id);
	}

	@Transactional(rollbackFor = DataAccessException.class)
	public Mono deleteById(Long id) {
		return repo.deleteById(id);
	}

	public Flux<Stu> list() {

		long repo_start_time = System.currentTimeMillis();
		Flux<Stu> all = repo.findAll();
		long repo_end_time = System.currentTimeMillis();

		long rpc_start_time = System.currentTimeMillis();
		Flux<Stu> stuFlux = WebClient.create("http://localhost:8081/api/v1/stu").get().retrieve().bodyToFlux(Stu.class);
		long rpc_end_time = System.currentTimeMillis();

		stuFlux.subscribe(this::consumer);

		System.out.println(String.format("repo cost: %d ms", repo_end_time - repo_start_time));
		System.out.println(String.format("rpc cost: %d ms", rpc_end_time - rpc_start_time));

		return all.concatWith(stuFlux);
	}

	private void consumer(Stu stu) {
		System.out.println(stu);
	}
}
