package com.jjlee.oauth.common.exception;

public class demoException extends RuntimeException {

    private final ErrorCode errorCode;

    public demoException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
