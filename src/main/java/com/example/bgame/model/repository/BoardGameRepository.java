package com.example.bgame.model.repository;


import com.example.bgame.model.internal.BoardGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {
    Page<BoardGame> findAllByNameContainsIgnoreCase(String name, Pageable pageable);

    Page<BoardGame> findAllByPublisherContainsIgnoreCase(String name, Pageable pageable);

    Page<BoardGame> findAllByPriceUkBetween(String minPrice, String maxPrice, Pageable pageable);

    Page<BoardGame> findAllByMinAgeAfter(int age, Pageable pageable);

}
