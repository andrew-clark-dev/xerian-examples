package com.xerian.examples.spring.validation.apierror.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xerian.examples.spring.validation.api.ProductUpdateRequest;
import com.xerian.examples.spring.validation.repository.ProductRepository;

@Component
public class ProductUpdateRequestValidator implements Validator {
//TODO    private ProductRepository productRepository;

    @Autowired
    public ProductUpdateRequestValidator(ProductRepository productRepository) {
//TODO        this.productRepository = productRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductUpdateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
//TODO    	ProductUpdateRequest productUpdateRequest = (ProductUpdateRequest) target;

//        if (!productRepository.existsById(productUpdateRequest.getSku())) {
//            errors.reject(ApiErrorCode.NOT_FOUND.getCode());
//        }
    }
}
