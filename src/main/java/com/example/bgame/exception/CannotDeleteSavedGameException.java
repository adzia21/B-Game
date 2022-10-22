package com.example.bgame.exception;

public class CannotDeleteSavedGameException extends RuntimeException{
    public CannotDeleteSavedGameException(Long id) {
        super("Cannot delete saved game with id: " + id + ". Access not allowed. ");
    }
}
