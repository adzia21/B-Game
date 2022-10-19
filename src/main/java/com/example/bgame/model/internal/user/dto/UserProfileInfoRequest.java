package com.example.bgame.model.internal.user.dto;

import com.example.bgame.model.internal.bgame.EBoardGameTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class UserProfileInfoRequest {

    private String description;

    private List<EBoardGameTypes> boardGameTypes;
    //TODO PHOTOS


}
