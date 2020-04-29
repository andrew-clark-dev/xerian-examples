package com.xerian.examples.spring.validation.apierror;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorsView {
    private List<ApiFieldError> fieldErrors;
    private List<ApiGlobalError> globalErrors;

    public ApiErrorsView() {
    }

    public ApiErrorsView(List<ApiFieldError> fieldErrors, List<ApiGlobalError> globalErrors) {
        this.fieldErrors = fieldErrors;
        this.globalErrors = globalErrors;
    }

    public List<ApiFieldError> getFieldErrors() {
        return fieldErrors;
    }

    public List<ApiGlobalError> getGlobalErrors() {
        return globalErrors;
    }

    public List<ApiFieldError> addFieldError(String field, String code, Object rejectedValue) {
        if (fieldErrors == null) fieldErrors = new ArrayList<>();
        fieldErrors.add(new ApiFieldError(field, code, rejectedValue));
        return fieldErrors;
    }
    public List<ApiGlobalError> addFieldError(String objectName, String code) {
        if (globalErrors == null) globalErrors = new ArrayList<>();
        globalErrors.add(new ApiGlobalError(objectName, code));
        return globalErrors;
    }

    public boolean isEmpty() {
    	final var fe = (fieldErrors==null?0:fieldErrors.size());
    	final var ge = (globalErrors==null?0:globalErrors.size());
    	return (fe + ge == 0);
    } 
}
