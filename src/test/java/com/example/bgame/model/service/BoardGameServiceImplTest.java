package com.example.bgame.model.service;

import com.example.bgame.model.internal.bgame.BoardGame;
import com.example.bgame.service.bgame.BoardGameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class BoardGameServiceImplTest {

    @Autowired
    BoardGameService boardGameService;

    @Test
    void shouldCalculatePricePlWhenPriceUkIsGiven() {
        //Given
        BoardGame boardGameWithPriceUk = BoardGame.builder()
                .name("Catan")
                .priceUk("20")
                .build();
        boardGameWithPriceUk.setPricePl("");

        //When
        boardGameService.saveBoardGame(boardGameWithPriceUk);
        String pricePl = boardGameService.getBoardGame(boardGameWithPriceUk.getId()).getPricePl();

        //Then
        assertEquals("90.0", pricePl);
    }
}