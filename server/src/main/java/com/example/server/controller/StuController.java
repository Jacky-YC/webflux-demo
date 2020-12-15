package com.example.server.controller;

import com.example.server.domain.Stu;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:18
 */
@RequestMapping("/api/v1/stu")
@RestController
@RequiredArgsConstructor
public class StuController {

//	private final StuService service;

	private final List<Stu> stus = List.of(new Stu("jacky", 24), new Stu("jason", 25), new Stu("franci", 22));

	@GetMapping
	public Flux<Stu> list() throws InterruptedException {
		Thread.sleep(5000);
		return Flux.just(new Stu("Jacky", 24));
	}

//	@PostMapping
//	public Mono save(@RequestBody Stu stu) {
//		return service.saveOrUpdate(stu);
//	}
//
//	@GetMapping("/{id}")
//	public Mono findById(@PathVariable Long id) {
//		return service.findById(id);
//	}
//
//	@PutMapping
//	public Mono update(@RequestBody Stu stu) {
//		return service.saveOrUpdate(stu);
//	}
//
//	@DeleteMapping("/{id}")
//	public Mono delete(@PathVariable Long id) {
//		return service.deleteById(id);
//	}

}
