package com.example.bgame.model.external;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Root {
    private ArrayList<Game> games;
    private int count;

}