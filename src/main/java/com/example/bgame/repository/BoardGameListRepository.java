package com.example.bgame.repository;

import com.example.bgame.model.internal.lists.BoardGameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardGameListRepository extends JpaRepository<BoardGameList, Long> {
    Optional<BoardGameList> findById(Long id);

}
