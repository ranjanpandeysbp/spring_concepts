package com.mycompany.springapp.productapp.exception;

public class BusinessException extends Exception {
    private final String errorCode;
    private final String errorMessage;

    public BusinessException(String errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
