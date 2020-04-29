package com.xerian.examples.spring.validation.apierror.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xerian.examples.spring.validation.api.ProductPatchRequest;
import com.xerian.examples.spring.validation.apierror.ApiErrorCode;

@Component
public class ProductPatchRequestValidator implements Validator {

    @Autowired
    public ProductPatchRequestValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductPatchRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ProductPatchRequest productPatchRequest = (ProductPatchRequest) target;
    	switch (productPatchRequest.getState()) {
		case READY:
		case CAPTURED:
		case SELLING :			
			break;
		default:
            errors.rejectValue("state", ApiErrorCode.UNKNOWN_STATE.getCode());
		} 
    }
}
