package com.xerian.examples.spring.validation.apierror.exception;

import org.springframework.http.HttpStatus;

import com.xerian.examples.spring.validation.apierror.ApiErrorsView;

public class ApiErrorException extends RuntimeException {

	public ApiErrorException() {
	}

	/**
	 * @param apiErrorsView
	 * @param httpStatus
	 */
	public ApiErrorException(ApiErrorsView apiErrorsView) {
		super();
		this.apiErrorsView = apiErrorsView;
	}

	/**
	 * @param apiErrorsView
	 * @param httpStatus
	 */
	public ApiErrorException(ApiErrorsView apiErrorsView, HttpStatus httpStatus) {
		super();
		this.apiErrorsView = apiErrorsView;
		this.httpStatus = httpStatus;
	}

	private static final long serialVersionUID = 1L;

	ApiErrorsView apiErrorsView;
	HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public ApiErrorsView getApiErrorsView() {
		return apiErrorsView;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
