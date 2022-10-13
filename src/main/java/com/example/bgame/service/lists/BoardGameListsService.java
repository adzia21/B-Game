package com.example.bgame.service.lists;

import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import com.example.bgame.model.internal.user.User;

public interface BoardGameListsService {
    BoardGameList getBoardGameListById(Long id);

    BoardGameList getBoardGameListByUserId(User user, Long id);

    BoardGameList saveBoardGameList(EBoardGameNamesList name);

    BoardGameList addBoardGameToList(Long BoardGameId, Long BoardGameListId);

    void deleteBoardGameFromList(Long id);
}
