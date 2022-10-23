package com.example.bgame.repository;

import com.example.bgame.model.internal.savedgame.SavedGame;
import com.example.bgame.model.internal.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SavedGameRepository extends JpaRepository<SavedGame, Long> {
    Optional<SavedGame> findById(Long id);
    List<SavedGame> findAllByPlayersContainsOrCreator(User player, User creator);
}
