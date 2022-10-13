package com.example.bgame.service.lists;

import com.example.bgame.exception.BoardGameListNotFoundException;
import com.example.bgame.exception.BoardGameNotFoundException;
import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import com.example.bgame.model.internal.user.User;
import com.example.bgame.repository.BoardGameListRepository;
import com.example.bgame.repository.BoardGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardGameListsServiceImpl implements BoardGameListsService {

    private BoardGameListRepository boardGameListRepository;
    private BoardGameRepository boardGameRepository;

    static BoardGameList unwrapBoardGameList(Optional<BoardGameList> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new BoardGameListNotFoundException(id);
    }

    @Override
    public BoardGameList saveBoardGameList(EBoardGameNamesList name) {
        BoardGameList boardGameList = new BoardGameList(name);
        return boardGameListRepository.save(boardGameList);
    }

    @Override
    public BoardGameList getBoardGameListById(Long id) {
        Optional<BoardGameList> boardGameList = boardGameListRepository.findById(id);
        return unwrapBoardGameList(boardGameList, id);
    }

    @Override
    public BoardGameList getBoardGameListByUserId(User user, Long id) {
        Long userId = user.getId();
        Optional<BoardGameList> boardGameList = boardGameListRepository.findById(userId);
        return unwrapBoardGameList(boardGameList, id);
    }

    @Override
    public BoardGameList addBoardGameToList(Long boardGameId, Long boardGameListId) {
        BoardGame boardGameFromRepo = boardGameRepository.findById(boardGameId).orElseThrow(() -> new BoardGameNotFoundException(boardGameId));
        BoardGameList boardGameList = getBoardGameListById(boardGameListId);
        boardGameList.getBoardGameList().add(boardGameFromRepo);
        return boardGameListRepository.save(boardGameList);
    }

    @Override
    public void deleteBoardGameFromList(Long id) {

    }

}
