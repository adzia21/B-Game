package com.example.bgame.model.repository;


import com.example.bgame.model.internal.BoardGame;
import org.springframework.data.repository.CrudRepository;

public interface BoardGameRepository extends CrudRepository<BoardGame, Long> {

}
