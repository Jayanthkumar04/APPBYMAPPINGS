package com.jay.bookstore.appbymappings.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    private final String reasonCode;

    public CustomerNotFoundException(String message, String reasonCode) {
        super(message);
        this.reasonCode = reasonCode;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public Object getErrorCode() {
    return getErrorCode();
    }
}