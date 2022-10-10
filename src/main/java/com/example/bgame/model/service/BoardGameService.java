package com.example.bgame.model.service;

import com.example.bgame.model.internal.BoardGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardGameService {
    BoardGame getBoardGame(Long id);

    BoardGame saveBoardGame(BoardGame boardGame);

    void deleteBoardGame(Long id);

    Page<BoardGame> getBoardGames(int pageNumber, int pageResultLimit);

    void updateBoardGame(BoardGame boardGame, Long id);

    void saveIntoDbBoardGameFromAPI();

    Page<BoardGame> findByName(String name, int page, int pageResultLimit);

    Page<BoardGame> findByPublisher(String name, int page, int pageResultLimit);

    Page<BoardGame> findByPrice(String minPrice, String maxPrice, int page, int pageResultLimit);

    Page<BoardGame> findByAge(int age, int page, int pageResultLimit);
}
