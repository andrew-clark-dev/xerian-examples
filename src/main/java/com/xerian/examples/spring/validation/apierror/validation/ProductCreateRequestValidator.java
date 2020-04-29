package com.xerian.examples.spring.validation.apierror.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xerian.examples.spring.validation.api.ProductCreateRequest;
import com.xerian.examples.spring.validation.apierror.ApiErrorCode;
import com.xerian.examples.spring.validation.repository.ProductRepository;

@Component
public class ProductCreateRequestValidator implements Validator {
    private ProductRepository productRepository;

    @Autowired
    public ProductCreateRequestValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductCreateRequest productCreateRequest = (ProductCreateRequest) target;

        if (productRepository.existsBySku(productCreateRequest.getSku())) {
            errors.reject(ApiErrorCode.ALREADY_EXISTS.getCode());
        }
    }
}
