package com.xerian.examples.spring.validation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xerian.examples.spring.validation.domain.Product;


public interface ProductRepository extends JpaRepository<Product, String> {
	
	Optional<Product> findBySku(String sku);
	boolean existsBySku(String sku);

}
