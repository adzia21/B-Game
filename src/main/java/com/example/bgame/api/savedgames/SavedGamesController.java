package com.example.bgame.api.savedgames;

import com.example.bgame.model.internal.savedgame.SavedGame;
import com.example.bgame.model.internal.savedgame.SavedGameRequest;
import com.example.bgame.service.savedgame.SavedGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    ResponseEntity<?> deleteSavedBoardGame(@PathVariable Long id){
        savedGameService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
