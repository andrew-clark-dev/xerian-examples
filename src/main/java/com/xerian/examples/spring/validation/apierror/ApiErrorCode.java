package com.xerian.examples.spring.validation.apierror;

public enum ApiErrorCode {

	ALREADY_EXISTS("AlreadyExists"),

	WRONG_STATE("WrongState"),

	UNKNOWN_STATE("UnknownState"),

	STOCK_EMPTY("StockEmpty"),

	NOT_FOUND("NotFound");

    private String code;

    ApiErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
