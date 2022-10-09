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

    @Override
    public void updateBoardGame(BoardGame boardGame, Long id) {
        BoardGame boardGameFromDb = getBoardGame(id);
        if(boardGame.getName() != null){
            boardGameFromDb.setName(boardGame.getName());
        }
        if(boardGame.getPriceUk() != null){
            boardGameFromDb.setPriceUk(boardGame.getPriceUk());
        }
        if(boardGame.getPricePl() != null){
            boardGameFromDb.setPricePl(boardGame.getPricePl());
        }
        if(boardGame.getPublishedYear() != 0){
            boardGameFromDb.setPublishedYear(boardGame.getPublishedYear());
        }

        if(boardGame.getMinPlayers() != 0){
            boardGameFromDb.setMinPlayers(boardGame.getMinPlayers());
        }
        if(boardGame.getMaxPlayers() != 0){
            boardGameFromDb.setMaxPlayers(boardGame.getMaxPlayers());
        }
        if(boardGame.getMinPlaytime() != 0){
            boardGameFromDb.setMinPlaytime(boardGame.getMinPlaytime());
        }
        if(boardGame.getMaxPlaytime() != 0){
            boardGameFromDb.setMaxPlaytime(boardGame.getMaxPlaytime());
        }
        if(boardGame.getPlaytime() != null){
            boardGameFromDb.setPlaytime(boardGame.getPlaytime());
        }
        if(boardGame.getMinAge() != 0){
            boardGameFromDb.setMinAge(boardGame.getMinAge());
        }
        if(boardGame.getDescription() != null){
            boardGameFromDb.setDescription(boardGame.getDescription());
        }
        if(boardGame.getCommentary() != null){
            boardGameFromDb.setCommentary(boardGame.getCommentary());
        }
        if(boardGame.getFaq() != null){
            boardGameFromDb.setFaq(boardGame.getFaq());
        }
        if(boardGame.getImageUrl() != null){
            boardGameFromDb.setImageUrl(boardGame.getImageUrl());
        }
        if(boardGame.getDesigner() != null){
            boardGameFromDb.setDesigner(boardGame.getDesigner());
        }
        if(boardGame.getArtists() != null){
            boardGameFromDb.setArtists(boardGame.getArtists());
        }
        boardGameRepository.save(boardGameFromDb);
    }

    static BoardGame unwrapBoardGame(Optional<BoardGame> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new BoardGameNotFoundException(id);
    }
}
