package com.example.bgame.service.savedgame;

import com.example.bgame.model.internal.savedgame.SavedGame;
import com.example.bgame.model.internal.savedgame.SavedGameRequest;

import java.util.Date;
import java.util.List;

public interface SavedGameService {
    List<SavedGame> getSavedGamesById(Long id);
    List<SavedGame> getSavedGamesByCreationDate(Date date);
    SavedGame save(SavedGameRequest savedGameRequest);

    SavedGame findById(Long id);

    void delete(Long savedGameId);
}
