package com.example.bgame.model.exception;

public class BoardGameNotFoundException extends RuntimeException {
    public BoardGameNotFoundException(Long id) {
        super("The board game id: " + id + "doesn't exists in our records.");
    }
}
