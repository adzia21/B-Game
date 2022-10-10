package com.example.bgame.model.service;

import com.example.bgame.Utils;
import com.example.bgame.model.exception.BoardGameNotFoundException;
import com.example.bgame.model.external.Root;
import com.example.bgame.model.internal.BoardGame;
import com.example.bgame.model.repository.BoardGameRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public Page<BoardGame> getBoardGames(int page, int pageResultLimit) {
        PageRequest pageRequest = PageRequest.of(page, pageResultLimit);
        return boardGameRepository.findAll(pageRequest);
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
    public void updateBoardGame(BoardGame boardGame, Long id) {
        BoardGame boardGameFromDb = getBoardGame(id);
        if (boardGame.getName() != null) {
            boardGameFromDb.setName(boardGame.getName());
        }
        if (boardGame.getPriceUk() != null) {
            boardGameFromDb.setPriceUk(boardGame.getPriceUk());
        }
        if (boardGame.getPricePl() != null) {
            boardGameFromDb.setPricePl(boardGame.getPricePl());
        }
        if (boardGame.getPublishedYear() != 0) {
            boardGameFromDb.setPublishedYear(boardGame.getPublishedYear());
        }

        if (boardGame.getMinPlayers() != 0) {
            boardGameFromDb.setMinPlayers(boardGame.getMinPlayers());
        }
        if (boardGame.getMaxPlayers() != 0) {
            boardGameFromDb.setMaxPlayers(boardGame.getMaxPlayers());
        }
        if (boardGame.getMinPlaytime() != 0) {
            boardGameFromDb.setMinPlaytime(boardGame.getMinPlaytime());
        }
        if (boardGame.getMaxPlaytime() != 0) {
            boardGameFromDb.setMaxPlaytime(boardGame.getMaxPlaytime());
        }
        if (boardGame.getPlaytime() != null) {
            boardGameFromDb.setPlaytime(boardGame.getPlaytime());
        }
        if (boardGame.getMinAge() != 0) {
            boardGameFromDb.setMinAge(boardGame.getMinAge());
        }
        if (boardGame.getDescription() != null) {
            boardGameFromDb.setDescription(boardGame.getDescription());
        }
        if (boardGame.getCommentary() != null) {
            boardGameFromDb.setCommentary(boardGame.getCommentary());
        }
        if (boardGame.getFaq() != null) {
            boardGameFromDb.setFaq(boardGame.getFaq());
        }
        if (boardGame.getImageUrl() != null) {
            boardGameFromDb.setImageUrl(boardGame.getImageUrl());
        }
        if (boardGame.getDesigner() != null) {
            boardGameFromDb.setDesigner(boardGame.getDesigner());
        }
        if (boardGame.getArtists() != null) {
            boardGameFromDb.setArtists(boardGame.getArtists());
        }
        boardGameRepository.save(boardGameFromDb);
    }

    @Override
    public void saveIntoDbBoardGameFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        Root object = restTemplate.getForObject("https://api.boardgameatlas.com/api/search?limit=100&client_id=HxiKNy5EnG", Root.class);
        assert object != null; // TODO
        List<BoardGame> boardGameListFromAPI = object.getGames().stream().map(game -> BoardGame.builder()
                .name(game.getName())
                .priceUk(game.getPrice_uk())
                .pricePl(String.valueOf(Double.parseDouble(game.getPrice_uk()) * 4.5))
                .publishedYear(game.getYear_published())
                .minPlayers(game.getMin_players())
                .maxPlayers(game.getMax_players())
                .minPlaytime(game.getMin_playtime())
                .maxPlaytime(game.getMax_playtime())
                .playtime(game.getPlaytime())
                .minAge(game.getMin_age())
                .description(Utils.removeHtmlCode(game.getDescription()))
                .commentary(Utils.removeHtmlCode(game.getCommentary()))
                .faq(Utils.removeHtmlCode(game.getFaq()))
                .imageUrl(game.getImage_url())
                .publisher(game.getPrimary_publisher().getName())
                .designer(game.getPrimary_designer().getName())
                .artists(game.getArtists())
                .build()).toList();
        boardGameRepository.saveAll(boardGameListFromAPI);
    }


    static BoardGame unwrapBoardGame(Optional<BoardGame> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new BoardGameNotFoundException(id);
    }

    public Page<BoardGame> findByName(String name, int page, int pageResultLimit) {
        PageRequest pageRequest = PageRequest.of(page, pageResultLimit);
        return boardGameRepository.findAllByNameContainsIgnoreCase(name, pageRequest);
    }

    public Page<BoardGame> findByPublisher(String name, int page, int pageResultLimit) {
        PageRequest pageRequest = PageRequest.of(page, pageResultLimit);
        return boardGameRepository.findAllByPublisherContainsIgnoreCase(name, pageRequest);
    }

    public Page<BoardGame> findByPrice(String minPrice, String maxPrice, int page, int pageResultLimit) {
        PageRequest pageRequest = PageRequest.of(page, pageResultLimit);
        return boardGameRepository.findAllByPriceUkBetween(minPrice, maxPrice, pageRequest);
    }

    @Override
    public Page<BoardGame> findByAge(int age, int page, int pageResultLimit) {
        PageRequest pageRequest = PageRequest.of(page, pageResultLimit);
        return boardGameRepository.findAllByMinAgeAfter(age, pageRequest);
    }

}
