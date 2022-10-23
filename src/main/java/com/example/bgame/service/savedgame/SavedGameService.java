package com.example.bgame.service.savedgame;

import com.example.bgame.model.internal.savedgame.SavedGame;
import com.example.bgame.model.internal.savedgame.SavedGameRequest;

import java.util.Date;
import java.util.List;

public interface SavedGameService {
    List<SavedGame> getSavedGamesById(Long id); //TODO
    List<SavedGame> getSavedGamesByCreationDate(Date date); //TODO
    SavedGame save(SavedGameRequest savedGameRequest);

    SavedGame findById(Long id);
    SavedGame addUserToGame(Long savedGameId, List<Long> userIds);

    List<SavedGame> findAllByLoggedUser();
    void delete(Long SavedGameId);

    void addComment(Long savedGameId, String description, Long commentId);
}
