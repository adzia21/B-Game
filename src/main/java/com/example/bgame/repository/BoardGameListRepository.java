package com.example.bgame.repository;

import com.example.bgame.model.internal.lists.BoardGameList;
import com.example.bgame.model.internal.lists.EBoardGameNamesList;
import com.example.bgame.model.internal.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardGameListRepository extends JpaRepository<BoardGameList, Long> {
    Optional<BoardGameList> findById(Long id);

    List<BoardGameList> findBoardGameListsByUser(User user);

    boolean existsByNamesListAndUser(EBoardGameNamesList name, User user);
}
