package com.example.bgame.exception;

public class ExternalApiConnectionException extends RuntimeException{
    public ExternalApiConnectionException() {
        super("External API doesn't respond.");
    }
}
