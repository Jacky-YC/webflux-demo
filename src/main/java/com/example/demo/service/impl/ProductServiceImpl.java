package com.example.demo.service.impl;

import com.example.demo.domain.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	@Override
	public Product save(Product product) {
		return repository.save(product);
	}

	@Override
	public void delete(Product product) {
		repository.delete(product);
	}

	@Override
	public void update(Product product) {
		repository.save(product);
	}

	@Override
	public void patch(Product product) {
		repository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Product> findById(Long id) {
		return repository.findById(id);
	}
}
