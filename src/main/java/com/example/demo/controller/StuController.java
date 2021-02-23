package com.example.demo.controller;

import com.example.demo.domain.Stu;
import com.example.demo.service.StuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Paths;

/**
 * @author: YeCheng
 * @date: 2020/11/10
 * @time: 17:18
 */
@RequestMapping("/stu")
@RestController
@RequiredArgsConstructor
public class StuController {

	private final StuService service;

	@GetMapping
	public Flux<Stu> list(@PageableDefault(page = 1, size = 10, sort = "id") Pageable pageable) {
		return service.list(pageable);
	}

	@PostMapping
	public Mono save(@RequestBody Stu stu) {
		return service.saveOrUpdate(stu);
	}

	@GetMapping("/{id}")
	public Mono findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PutMapping
	public Mono update(@RequestBody Stu stu) {
		return service.saveOrUpdate(stu);
	}

	@DeleteMapping("/{id}")
	public Mono delete(@PathVariable Long id) {
		return service.deleteById(id);
	}

	@GetMapping("/export")
	public void test(ServerHttpResponse response) {
		response.getHeaders().add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=price.json");
		response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM);
		ZeroCopyHttpOutputMessage response1 = (ZeroCopyHttpOutputMessage) response;
		response1.writeWith(Paths.get("/billing/price"), 0, Paths.get("/billing/price").toFile().length());
	}

}
