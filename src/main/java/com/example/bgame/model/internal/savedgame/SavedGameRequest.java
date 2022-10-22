package com.example.bgame.model.internal.savedgame;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SavedGameRequest {
    private String name;
    private String description;
    private Long boardGameId;
}
