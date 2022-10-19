package com.example.bgame.exception;

public class AccessNotAllowedException extends RuntimeException {
    public AccessNotAllowedException(Long id) {
        super("Cannot access data user with id " + id);
    }
}
