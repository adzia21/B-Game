package com.example.bgame.model.internal.user.dto;

import com.example.bgame.model.internal.bgame.EBoardGameTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@AllArgsConstructor
@Setter
public class UserProfileInfoRequest {

    private String description;

    private Set<EBoardGameTypes> boardGameTypes;
    //TODO PHOTOS
}
