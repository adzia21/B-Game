package com.example.bgame.api.lists;

import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import com.example.bgame.service.lists.BoardGameListsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/boardgamelists")
public class BoardGameListsController {
    BoardGameListsService boardGameListsService;

    @PostMapping("/create")
    public ResponseEntity<BoardGameList> createBoardGameList(@Valid @RequestParam("name") EBoardGameNamesList name) {
        return new ResponseEntity<>(boardGameListsService.saveBoardGameList(name), HttpStatus.CREATED);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<BoardGameList> getBoardGameListById(@PathVariable Long gameId) {
        return new ResponseEntity<>(boardGameListsService.getBoardGameListById(gameId), HttpStatus.OK);
    }

    @GetMapping("{boardGameListId}/add/{boardGameId}")
    public ResponseEntity<BoardGameList> saveBoardGameToList(@PathVariable Long boardGameListId, @PathVariable Long boardGameId) {
        return new ResponseEntity<>(boardGameListsService.addBoardGameToList(boardGameId, boardGameListId), HttpStatus.CREATED);
    }


}
