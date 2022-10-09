package com.example.bgame.model.api;


import com.example.bgame.model.internal.BoardGame;
import com.example.bgame.model.service.BoardGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/boardGame")
public class BoardGameController {
    BoardGameService boardGameService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardGame> getBoardGame(@PathVariable Long id){
        return new ResponseEntity<>(boardGameService.getBoardGame(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardGame> saveBoardGame(@Valid @RequestBody BoardGame boardGame){
        return new ResponseEntity<>(boardGameService.saveBoardGame(boardGame), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardGame> deleteBoardGame(@PathVariable Long id){
        boardGameService.deleteBoardGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BoardGame>> getBoardGames(){
        return new ResponseEntity<>(boardGameService.getBoardGames(), HttpStatus.OK);
    }
}
