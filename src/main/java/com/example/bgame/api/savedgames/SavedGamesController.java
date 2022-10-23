package com.example.bgame.api.savedgames;

import com.example.bgame.model.internal.comments.dto.CommentRequest;
import com.example.bgame.model.internal.savedgame.SavedGame;
import com.example.bgame.model.internal.savedgame.SavedGameRequest;
import com.example.bgame.service.savedgame.SavedGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/savedGames")
public class SavedGamesController {
    SavedGameService savedGameService;

    @PostMapping()
    ResponseEntity<SavedGame> createSavedBoardGame(@Valid @RequestBody SavedGameRequest request){
        return new ResponseEntity<>(savedGameService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getSavedBoardGameById(@PathVariable Long id){
        return new ResponseEntity<>(savedGameService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteSavedBoardGame(@PathVariable Long id){
        savedGameService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // INSIDE LISTS

    @PutMapping("/{id}")
    ResponseEntity<SavedGame> addPlayersToSavedBoardGame(@PathVariable Long id, @RequestBody List<Long> userIds){
        return new ResponseEntity<>(savedGameService.addUserToGame(id, userIds), HttpStatus.OK);
    }

    @GetMapping()
    ResponseEntity<List<SavedGame>> findAllByLoggedUser(){
        return new ResponseEntity<>(savedGameService.findAllByLoggedUser(), HttpStatus.OK);
    }

    @PutMapping("/comment/{savedGameId}")
    ResponseEntity<Object> addComment(@RequestBody CommentRequest request, @PathVariable Long savedGameId){
        savedGameService.addComment(savedGameId, request.getDescription(), request.getSuperiorCommentId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
