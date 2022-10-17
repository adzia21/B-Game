package com.example.bgame.service.lists;

import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;

import java.util.List;

public interface BoardGameListsService {
    BoardGameList getBoardGameListById(Long id);

    BoardGameList saveBoardGameList(EBoardGameNamesList name);

    List<BoardGameList> getBoardGameListsByLoggedUser();

    BoardGameList addBoardGameToList(Long BoardGameId, Long BoardGameListId);

    void deleteBoardGameFromList(Long id);

    void deleteBoardGameList(Long id);
}
