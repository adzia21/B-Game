package com.example.bgame.exception;

import com.example.bgame.model.internal.lists.EBoardGameNamesList;

public class BoardGameListAlreadyExistException extends RuntimeException {
    public BoardGameListAlreadyExistException(EBoardGameNamesList name) {
        super("Board game list: " + name + " already exists.");
    }
}
