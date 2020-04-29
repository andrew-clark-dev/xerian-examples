package com.xerian.examples.spring.validation.error.validation.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import com.xerian.examples.spring.validation.api.ProductPatchRequest;
import com.xerian.examples.spring.validation.apierror.ApiErrorCode;
import com.xerian.examples.spring.validation.apierror.ApiErrorsView;
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
		ApiErrorsView apiErrorsView = new ApiErrorsView();
		switch (productPatchRequest.getState()) {
		case CAPTURED:
			apiErrorsView.addFieldError("state", ApiErrorCode.WRONG_STATE.getCode(), "CAPTURED");
			break;
		case READY:
			if (!State.CAPTURED.equals(product.getState())) {
				apiErrorsView.addFieldError("state", ApiErrorCode.WRONG_STATE.getCode(), "READY");
			}
			if (product.getStock() < 1) {
				apiErrorsView.addFieldError("stock", ApiErrorCode.STOCK_EMPTY.getCode(), product.getStock());
			}
			break;
		case SELLING:
			break;
		default:
			apiErrorsView.addFieldError("state", ApiErrorCode.UNKNOWN_STATE.getCode(), productPatchRequest.getState());
		}

		if (!apiErrorsView.isEmpty()) {
			throw new ApiErrorException(apiErrorsView);
		}
	}
}
