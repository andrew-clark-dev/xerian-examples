package com.xerian.examples.spring.validation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xerian.examples.spring.validation.api.ProductCreateRequest;
import com.xerian.examples.spring.validation.api.ProductPatchRequest;
import com.xerian.examples.spring.validation.api.ProductUpdateRequest;
import com.xerian.examples.spring.validation.apierror.exception.NotFound;
import com.xerian.examples.spring.validation.domain.Product;
import com.xerian.examples.spring.validation.error.validation.service.ProductValidatorService;
import com.xerian.examples.spring.validation.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository repository;
	private ProductValidatorService productValidatorService;

	@Autowired
	public ProductService(ProductRepository productRepository, ProductValidatorService productValidatorService) {
		this.repository = productRepository;
		this.productValidatorService = productValidatorService;
	}

	public List<Product> findAll() {
		List<Product> products = repository.findAll();
		return products;
	}

	public Product find(String sku) {
		Product product = repository.findBySku(sku).orElseThrow(() -> new NotFound("Product"));
		return product;
	}

	public Product create(ProductCreateRequest productCreateRequest) {
		Product product = productCreateRequest.toProduct();
		return repository.save(product);
	}

	public Product update(String sku, ProductUpdateRequest productUpdateRequest) {
		Product product = find(sku);
		Product updatedProduct = productUpdateRequest.updateProduct(product);

		return repository.save(updatedProduct);
	}

	public Product patch(String sku, ProductPatchRequest productPatchRequest) {
		Product product = find(sku);
		
		productValidatorService.validatePatch(productPatchRequest, product);
		
		Product patchedProduct = productPatchRequest.patchProduct(product);

		return repository.save(patchedProduct);
	}

}
