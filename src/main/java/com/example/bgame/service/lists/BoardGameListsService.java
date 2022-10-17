package com.example.bgame.service.lists;

import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;

import java.util.List;

public interface BoardGameListsService {
    //LISTS
    List<BoardGameList> getBoardGameListsByLoggedUser();

    BoardGameList saveBoardGameList(EBoardGameNamesList name);

    void deleteBoardGameList(Long id);

    //INSIDE LISTS
    BoardGameList getBoardGameListById(Long id);

    BoardGameList addBoardGameToList(Long BoardGameId, Long BoardGameListId);

    void deleteBoardGameFromList(Long boardGameId, Long boardGameListId);
}
