package com.example.bgame.exception;

public class SavedGameNotFoundException extends RuntimeException{
    public SavedGameNotFoundException(Long id) {
        super("Saved game with id: " + id + " not found.");
    }
}
