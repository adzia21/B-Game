package com.example.bgame.exception;

public class BoardGameListNotFoundException extends RuntimeException {
    public BoardGameListNotFoundException(Long id) {
        super("Board game list with id: " + id + "not found");
    }
}
