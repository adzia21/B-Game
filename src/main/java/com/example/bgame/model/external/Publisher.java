package com.example.bgame.model.external;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Publisher{
    private String id;
    private Object num_games;
    private int score;
    private Game game;
    private String url;
    private Images images;
}
