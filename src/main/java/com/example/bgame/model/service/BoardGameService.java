package com.example.bgame.model.service;

import com.example.bgame.model.internal.BoardGame;

import java.util.List;

public interface BoardGameService {
    BoardGame getBoardGame(Long id);
    BoardGame saveBoardGame(BoardGame boardGame);
    void deleteBoardGame(Long id);
    List<BoardGame> getBoardGames();


}
