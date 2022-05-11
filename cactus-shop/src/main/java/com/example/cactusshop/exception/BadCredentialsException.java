package com.example.cactusshop.exception;

public class BadCredentialsException extends RuntimeException {

    private final String errorCode;

    public BadCredentialsException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
