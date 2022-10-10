package com.example.bgame.model.exception;

public class ExternalApiConnectionException extends RuntimeException{
    public ExternalApiConnectionException() {
        super("External API doesn't respond.");
    }
}
