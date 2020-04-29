package com.xerian.examples.spring.validation.apierror.exception;

import org.springframework.http.HttpStatus;

import com.xerian.examples.spring.validation.apierror.ApiErrors;

public class ApiErrorException extends RuntimeException {

	public ApiErrorException() {
	}

	/**
	 * @param apiErrors
	 * @param httpStatus
	 */
	public ApiErrorException(ApiErrors apiErrors) {
		super();
		this.apiErrors = apiErrors;
	}

	/**
	 * @param apiErrors
	 * @param httpStatus
	 */
	public ApiErrorException(ApiErrors apiErrors, HttpStatus httpStatus) {
		super();
		this.apiErrors = apiErrors;
		this.httpStatus = httpStatus;
	}

	private static final long serialVersionUID = 1L;

	ApiErrors apiErrors;
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public ApiErrors getapiErrors() {
		return apiErrors;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
