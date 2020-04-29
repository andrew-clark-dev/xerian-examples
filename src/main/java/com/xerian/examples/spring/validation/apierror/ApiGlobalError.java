package com.xerian.examples.spring.validation.apierror;

public class ApiGlobalError {
    private String objectName;
    private String code;

    public ApiGlobalError(String objectName, String code) {
        this.code = code;
        this.objectName = objectName;
    }

    public String getCode() {
        return code;
    }

    public String getObjectName() {
        return objectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiGlobalError that = (ApiGlobalError) o;

        return code != null ? code.equals(that.code) : that.code == null;

    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
