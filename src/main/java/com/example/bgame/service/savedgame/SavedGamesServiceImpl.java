package com.example.bgame.service.savedgame;

import com.example.bgame.exception.AccessNotAllowedException;
import com.example.bgame.exception.BoardGameNotFoundException;
import com.example.bgame.exception.SavedGameNotFoundException;
import com.example.bgame.exception.UserNotFoundException;
import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.model.internal.comments.Comment;
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
import java.util.Set;
import java.util.stream.Collectors;


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
        Long loggedUserId = getLoggedUserId();
        SavedGame savedGame = savedGameRepository.findById(id).orElseThrow(() -> new SavedGameNotFoundException(id));
        if(!savedGame.getCreator().getId().equals(loggedUserId) || savedGame.getPlayers().stream().noneMatch((p->p.getId().equals(loggedUserId)))){
            throw new AccessNotAllowedException(loggedUserId);
        }
        return savedGame;
    }

    @Override
    public SavedGame addUserToGame(Long savedGameId, List<Long> userIds) {

        SavedGame savedGame = savedGameRepository.findById(savedGameId)
                .orElseThrow(() -> new SavedGameNotFoundException(savedGameId));

        userIds.remove(savedGame.getCreator().getId());

        Set<User> userList = userIds.stream().map(userId -> userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)))
                .collect(Collectors.toSet());

        savedGame.setPlayers(userList);

        return savedGameRepository.save(savedGame);
    }

    @Override
    public List<SavedGame> findAllByLoggedUser() {
        Long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(()-> new UserNotFoundException(loggedUserId));
        return savedGameRepository.findAllByPlayersContainsOrCreator(user, user);
    }

    @Override
    public void delete(Long savedGameId) {
        Long loggedUserId = getLoggedUserId();
        SavedGame savedGame = savedGameRepository.findById(savedGameId)
                .orElseThrow(() -> new SavedGameNotFoundException(savedGameId));
        if(loggedUserId.equals(savedGame.getCreator().getId())){
            savedGameRepository.delete(savedGame);
        }else{
            savedGame.getPlayers().remove(userRepository.findById(loggedUserId)
                    .orElseThrow(()-> new UserNotFoundException(loggedUserId)));
            savedGameRepository.save(savedGame);
        }
    }

    @Override
    public void addComment(Long savedGameId, String description, Long commentId) {
        Long loggedUserId = getLoggedUserId();
        User user = userRepository.findById(loggedUserId).orElseThrow(() -> new UserNotFoundException(loggedUserId));
        SavedGame savedGame = findById(savedGameId);
        Comment comment = new Comment(user, description);
        if(commentId != null){
            comment.setSuperiorCommentId(commentId);
        }
        savedGame.getComments().add(comment);
        savedGameRepository.save(savedGame);
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
