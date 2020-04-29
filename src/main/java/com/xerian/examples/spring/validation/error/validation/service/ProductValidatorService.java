package com.xerian.examples.spring.validation.error.validation.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.xerian.examples.spring.validation.api.ProductPatchRequest;
import com.xerian.examples.spring.validation.apierror.ApiErrorCode;
import com.xerian.examples.spring.validation.apierror.ApiErrors;
import com.xerian.examples.spring.validation.apierror.exception.ApiErrorException;
import com.xerian.examples.spring.validation.domain.Product;
import com.xerian.examples.spring.validation.domain.State;

@Service
public class ProductValidatorService {

	private Validator validator;

	ProductValidatorService(Validator validator) {
		this.validator = validator;
	}

	public void validatePatch(ProductPatchRequest productPatchRequest, Product product) {
		// Check states
		ApiErrors apiErrors = new ApiErrors();
		switch (productPatchRequest.getState()) {
		case CAPTURED:
			apiErrors.addFieldError("state", ApiErrorCode.WRONG_STATE.getCode(), "CAPTURED");
			break;
		case READY:
			if (!State.CAPTURED.equals(product.getState())) {
				apiErrors.addFieldError("state", ApiErrorCode.WRONG_STATE.getCode(), "READY");
			}
			if (product.getStock() < 1) {
				apiErrors.addFieldError("stock", ApiErrorCode.STOCK_EMPTY.getCode(), product.getStock());
			}
			break;
		case SELLING:
			break;
		default:
			apiErrors.addFieldError("state", ApiErrorCode.UNKNOWN_STATE.getCode(), productPatchRequest.getState());
		}

		if (!apiErrors.isEmpty()) {
			throw new ApiErrorException(apiErrors);
		}
	}
}
