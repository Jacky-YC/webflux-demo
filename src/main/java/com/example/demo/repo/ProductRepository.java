package com.example.demo.repo;

import com.example.demo.domain.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProductRepository extends R2dbcRepository<Product, Long> {

}
