package com.example.bgame.model.internal.lists.dto;

import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class BoardGameListWithoutUserResponse {
    private Long id;
    private EBoardGameNamesList namesList;
    private Set<BoardGame> boardGameList;

    public static BoardGameListWithoutUserResponse from(BoardGameList boardGameList) {
        return new BoardGameListWithoutUserResponse(
                boardGameList.getId(),
                boardGameList.getNamesList(),
                boardGameList.getBoardGameList());
    }
}
