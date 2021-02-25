package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	@Override
	@Transactional
	public Mono<Product> save(Product product) {
		return repository.save(product);
	}

	@Override
	@Transactional
	public Mono<Void> delete(Product product) {
		return repository.delete(product);
	}

	@Override
	public Mono<Product> update(Product product) {
		return repository.save(product);
	}

	@Override
	@Transactional
	public Mono<Product> patch(Product product) {
		return repository.save(product);
	}

	@Override
	public Flux<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Product> findById(Long id) {
		return repository.findById(id);
	}
}
