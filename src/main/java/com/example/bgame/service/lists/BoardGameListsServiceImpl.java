package com.example.bgame.service.lists;

import com.example.bgame.exception.BoardGameListAlreadyExistException;
import com.example.bgame.exception.BoardGameListNotFoundException;
import com.example.bgame.exception.BoardGameNotFoundException;
import com.example.bgame.exception.UserNotFoundException;
import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import com.example.bgame.model.internal.user.User;
import com.example.bgame.repository.BoardGameListRepository;
import com.example.bgame.repository.BoardGameRepository;
import com.example.bgame.repository.UserRepository;
import com.example.bgame.service.user.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class BoardGameListsServiceImpl implements BoardGameListsService {

    private BoardGameListRepository boardGameListRepository;
    private BoardGameRepository boardGameRepository;
    private UserRepository userRepository;

    //LISTS
    @Override
    public List<BoardGameList> getBoardGameListsByLoggedUser() {
        Long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(() -> new UserNotFoundException(loggedUserId));
        return boardGameListRepository.findBoardGameListsByUser(user);
    }

    @Override
    public BoardGameList saveBoardGameList(EBoardGameNamesList name) {
        Long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(() -> new UserNotFoundException(loggedUserId)); //TODO validation
        if (boardGameListRepository.existsByNamesListAndUser(name, user)) {
            throw new BoardGameListAlreadyExistException(name);
        }
        BoardGameList boardGameList = new BoardGameList(name);
        boardGameList.setUser(user);
        return boardGameListRepository.save(boardGameList);
    }

    public void deleteBoardGameList(Long idListToDelete) {
        //TODO user validation
        BoardGameList boardGameList = boardGameListRepository.findById(idListToDelete).orElseThrow(() -> new BoardGameListNotFoundException(idListToDelete));
        boardGameListRepository.delete(boardGameList);
    }

    //INSIDE LISTS
    @Override
    public BoardGameList getBoardGameListById(Long id) {
        return boardGameListRepository.findById(id).orElseThrow(() -> new BoardGameListNotFoundException(id));
    }

    @Override
    public BoardGameList addBoardGameToList(Long boardGameId, Long boardGameListId) {
        BoardGame boardGameFromRepo = boardGameRepository.findById(boardGameId).orElseThrow(() -> new BoardGameNotFoundException(boardGameId));
        BoardGameList boardGameList = getBoardGameListById(boardGameListId);
        boardGameList.getBoardGameList().add(boardGameFromRepo);
        return boardGameListRepository.save(boardGameList);
    }

    @Override
    public void deleteBoardGameFromList(Long boardGameId, Long boardGameListId) {
        BoardGame boardGameFromRepo = boardGameRepository.findById(boardGameId).orElseThrow(() -> new BoardGameNotFoundException(boardGameId));
        BoardGameList boardGameList = getBoardGameListById(boardGameListId);
        boardGameList.getBoardGameList().remove(boardGameFromRepo);
        boardGameListRepository.save(boardGameList);
    }

    private static Long getLoggedUserId() {
        UserDetailsImpl loggedUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loggedUser.getId();
    }
}
