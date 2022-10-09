package com.example.bgame.model.service;

import com.example.bgame.model.exception.BoardGameNotFoundException;
import com.example.bgame.model.internal.BoardGame;
import com.example.bgame.model.repository.BoardGameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardGameServiceImpl implements BoardGameService {

    BoardGameRepository boardGameRepository;

    @Override
    public BoardGame getBoardGame(Long id) {
        Optional<BoardGame> boardGame = boardGameRepository.findById(id);
        return unwrapBoardGame(boardGame, id);
    }

    @Override
    public BoardGame saveBoardGame(BoardGame boardGame) {
        return boardGameRepository.save(boardGame);
    }

    @Override
    public void deleteBoardGame(Long id) {
        boardGameRepository.deleteById(id);
    }

    @Override
    public List<BoardGame> getBoardGames() {
        return (List<BoardGame>) boardGameRepository.findAll();
    }

    static BoardGame unwrapBoardGame(Optional<BoardGame> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new BoardGameNotFoundException(id);
    }
}
