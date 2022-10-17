package com.example.bgame.api.lists;

import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import com.example.bgame.model.internal.lists.dto.BoardGameListWithoutUserResponse;
import com.example.bgame.service.lists.BoardGameListsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/boardgamelists")
public class BoardGameListsController {
    private BoardGameListsService boardGameListsService;

    //DONE

    @PostMapping("/create")
    public ResponseEntity<BoardGameListWithoutUserResponse> createBoardGameList(@Valid @RequestParam("name") EBoardGameNamesList name) {
        BoardGameListWithoutUserResponse response = BoardGameListWithoutUserResponse.from(boardGameListsService.saveBoardGameList(name));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<BoardGameListWithoutUserResponse>> getBoardGameListByLoggedUser() {
        List<BoardGameList> boardGameListsByLoggedUser = boardGameListsService.getBoardGameListsByLoggedUser();
        List<BoardGameListWithoutUserResponse> response = boardGameListsByLoggedUser.stream()
                .map(BoardGameListWithoutUserResponse::from).toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBoardGameListByLoggedUser(@PathVariable Long id) {
        boardGameListsService.deleteBoardGameList(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //TODO

    @GetMapping("{boardGameListId}/add/{boardGameId}")
    public ResponseEntity<BoardGameList> saveBoardGameToList(@PathVariable Long boardGameListId, @PathVariable Long boardGameId) {
        return new ResponseEntity<>(boardGameListsService.addBoardGameToList(boardGameId, boardGameListId), HttpStatus.CREATED);
    }

}
