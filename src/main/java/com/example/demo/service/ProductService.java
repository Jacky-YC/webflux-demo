package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	Product save(Product product);

	void delete(Product product);

	void update(Product product);

	void patch(Product product);

	List<Product> findAll();

	Optional<Product> findById(Long id);

}
