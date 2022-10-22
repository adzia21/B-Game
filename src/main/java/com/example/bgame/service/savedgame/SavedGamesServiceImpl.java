package com.example.bgame.service.savedgame;

import com.example.bgame.exception.BoardGameNotFoundException;
import com.example.bgame.exception.CannotDeleteSavedGameException;
import com.example.bgame.exception.SavedGameNotFoundException;
import com.example.bgame.exception.UserNotFoundException;
import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.savedgame.SavedGame;
import com.example.bgame.model.internal.savedgame.SavedGameRequest;
import com.example.bgame.model.internal.user.User;
import com.example.bgame.repository.BoardGameRepository;
import com.example.bgame.repository.SavedGameRepository;
import com.example.bgame.repository.UserRepository;
import com.example.bgame.service.user.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Service
public class SavedGamesServiceImpl implements SavedGameService{

    BoardGameRepository boardGameRepository;
    SavedGameRepository savedGameRepository;
    UserRepository userRepository;

    //ALL LIST

    @Override
    public SavedGame save(SavedGameRequest savedGameRequest) {
        Long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(() -> new UserNotFoundException(loggedUserId));
        BoardGame boardGame = boardGameRepository.findById(savedGameRequest.getBoardGameId())
                .orElseThrow(() -> new BoardGameNotFoundException(savedGameRequest.getBoardGameId()));
        SavedGame savedGame = SavedGame.builder()
                .creator(user)
                .name(savedGameRequest.getName())
                .description(savedGameRequest.getDescription())
                .boardGame(boardGame)
                .build();
        return savedGameRepository.save(savedGame);
    }

    @Override
    public SavedGame findById(Long id) {
        return savedGameRepository.findById(id).orElseThrow(() -> new SavedGameNotFoundException(id));
    }

    @Override
    public void delete(Long savedGameId) {
        Long loggedUserId = getLoggedUserId();
        SavedGame savedGame = savedGameRepository.findById(savedGameId)
                .orElseThrow(() -> new SavedGameNotFoundException(savedGameId));
        if(!loggedUserId.equals(savedGame.getCreator().getId())){
            throw new CannotDeleteSavedGameException(savedGameId);
        }
        savedGameRepository.delete(savedGame);
    }

    @Override
    public List<SavedGame> getSavedGamesById(Long id) {
        return null;
    }

    @Override
    public List<SavedGame> getSavedGamesByCreationDate(Date date) {
        return null;
    }


    private static Long getLoggedUserId() {
        UserDetailsImpl loggedUser = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return loggedUser.getId();
    }

}
