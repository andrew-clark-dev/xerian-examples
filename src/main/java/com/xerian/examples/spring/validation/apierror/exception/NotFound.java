package com.xerian.examples.spring.validation.apierror.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.xerian.examples.spring.validation.apierror.ApiErrorCode;
import com.xerian.examples.spring.validation.apierror.ApiErrors;
import com.xerian.examples.spring.validation.apierror.ApiGlobalError;

public class NotFound extends ApiErrorException {

	private static final long serialVersionUID = 1L;

	public NotFound(String objectName) {
		ApiGlobalError apiGlobalError = new ApiGlobalError(objectName, ApiErrorCode.NOT_FOUND.getCode());
		apiErrors = new ApiErrors(null, List.of(apiGlobalError));
		httpStatus = HttpStatus.NOT_FOUND;
	}

}
