package com.example.demo.service;

import com.example.demo.domain.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

	Mono<Product> save(Product product);

	Mono<Void> delete(Product product);

	Mono<Product> update(Product product);

	Mono<Product> patch(Product product);

	Flux<Product> findAll();

	Mono<Product> findById(Long id);

}
