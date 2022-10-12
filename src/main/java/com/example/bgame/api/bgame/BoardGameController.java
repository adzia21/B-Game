package com.example.bgame.api.bgame;


import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.service.bgame.BoardGameService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/boardGame")
public class BoardGameController {
    BoardGameService boardGameService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardGame> getBoardGame(@PathVariable Long id) {
        return new ResponseEntity<>(boardGameService.getBoardGame(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardGame> saveBoardGame(@Valid @RequestBody BoardGame boardGame) {
        return new ResponseEntity<>(boardGameService.saveBoardGame(boardGame), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardGame> deleteBoardGame(@PathVariable Long id) {
        boardGameService.deleteBoardGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/{page}/{limit}")
    public ResponseEntity<Page<BoardGame>> getBoardGames(@PathVariable int page, @PathVariable int limit) {
        return new ResponseEntity<>(boardGameService.getBoardGames(page, limit), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardGame> updateBoardGame(@Valid @RequestBody BoardGame boardGame, @PathVariable Long id) {
        boardGameService.updateBoardGame(boardGame, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/fromAPI")
    public ResponseEntity<BoardGame> updateBoardGamesFromApi() {
        boardGameService.saveIntoDbBoardGameFromAPI();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<Page<BoardGame>> findByName(@RequestParam Map<String, String> params) {
        String name = params.get("name");
        String page = params.get("page");
        String limit = params.get("limit");
        return new ResponseEntity<>(boardGameService.findByName(name, Integer.parseInt(page), Integer.parseInt(limit)), HttpStatus.OK);
    }

    @GetMapping("/publisher")
    public ResponseEntity<Page<BoardGame>> findByPublisher(@RequestParam Map<String, String> params) {
        String name = params.get("publisher");
        String page = params.get("page");
        String limit = params.get("limit");
        return new ResponseEntity<>(boardGameService.findByPublisher(name, Integer.parseInt(page), Integer.parseInt(limit)), HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<Page<BoardGame>> findByPrice(@RequestParam Map<String, String> params) {
        String minPrice = params.get("minPrice");
        String maxPrice = params.get("maxPrice");
        String page = params.get("page");
        String limit = params.get("limit");
        return new ResponseEntity<>(boardGameService.findByPrice(minPrice, maxPrice, Integer.parseInt(page), Integer.parseInt(limit)), HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<Page<BoardGame>> findByAge(@RequestParam Map<String, String> params) {
        int age = Integer.parseInt(params.get("age"));
        String page = params.get("page");
        String limit = params.get("limit");
        return new ResponseEntity<>(boardGameService.findByAge(age, Integer.parseInt(page), Integer.parseInt(limit)), HttpStatus.OK);
    }
}
