package com.example.commerce.exception;

public class MemberNotEmailAuthException extends RuntimeException {
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
