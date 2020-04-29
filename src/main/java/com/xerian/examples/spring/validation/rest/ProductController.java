package com.xerian.examples.spring.validation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.xerian.examples.spring.validation.api.ProductCreateRequest;
import com.xerian.examples.spring.validation.api.ProductPatchRequest;
import com.xerian.examples.spring.validation.api.ProductUpdateRequest;
import com.xerian.examples.spring.validation.apierror.validation.ProductCreateRequestValidator;
import com.xerian.examples.spring.validation.apierror.validation.ProductPatchRequestValidator;
import com.xerian.examples.spring.validation.apierror.validation.ProductUpdateRequestValidator;
import com.xerian.examples.spring.validation.domain.Product;
import com.xerian.examples.spring.validation.service.ProductService;

import java.util.List;

import javax.validation.Valid;

@RestController
public class ProductController {
    private ProductCreateRequestValidator productCreateRequestValidator;
    private ProductUpdateRequestValidator productUpdateRequestValidator;
    private ProductPatchRequestValidator productPatchRequestValidator;
    private ProductService service;

    @Autowired
    public ProductController(
            ProductCreateRequestValidator productCreateRequestValidator,
            ProductUpdateRequestValidator productUpdateRequestValidator,
            ProductPatchRequestValidator productPatchRequestValidator,
            ProductService productService
    ) {
        this.productCreateRequestValidator = productCreateRequestValidator;
        this.productUpdateRequestValidator = productUpdateRequestValidator;
        this.productPatchRequestValidator = productPatchRequestValidator;
        this.service = productService;
    }

    @InitBinder("productCreateRequest")
    public void initCreateBinder(WebDataBinder binder) {
        binder.addValidators(productCreateRequestValidator);
    }

    @InitBinder("productUpdateRequest")
    public void initUpdateBinder(WebDataBinder binder) {
        binder.addValidators(productUpdateRequestValidator);
    }

    @InitBinder("productPatchRequest")
    public void initPatchBinder(WebDataBinder binder) {
        binder.addValidators(productPatchRequestValidator);
    }
    @RequestMapping(value = "/products/{sku}", method = RequestMethod.GET)
    public Product find(@PathVariable("sku") String sku) {
    	return service.find(sku);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> findAll() {
    	return service.findAll();
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product create(@Valid @RequestBody ProductCreateRequest productCreateRequest) {
    	return service.create(productCreateRequest);
    }

    @RequestMapping(value = "/products/{sku}", method = RequestMethod.PUT)
    public Product update(@PathVariable("sku") String sku, @Valid @RequestBody ProductUpdateRequest productUpdateRequest) {
    	return service.update(sku, productUpdateRequest);
    }

    @RequestMapping(value = "/products/{sku}", method = RequestMethod.PATCH)
    public Product patch(@PathVariable("sku") String sku, @Valid @RequestBody ProductPatchRequest productPatchRequest) {
    	return service.patch(sku, productPatchRequest);
    }
}
