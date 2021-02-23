package com.example.demo.service;

import com.example.demo.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Product> save(Product product);

	void delete(Product product);

	void update(Product product);

	void patch(Product product);

	Flux<Product> findAll();

	Mono<Product> findById(Long id);

}
