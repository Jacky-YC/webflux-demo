package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	@Override
	public Mono<Product> save(Product product) {
		return repository.save(product);
	}

	@Override
	public void delete(Product product) {
		repository.delete(product).subscribe();
	}

	@Override
	public void update(Product product) {
		repository.save(product).subscribe();
	}

	@Override
	public void patch(Product product) {
		repository.save(product).subscribe();
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
