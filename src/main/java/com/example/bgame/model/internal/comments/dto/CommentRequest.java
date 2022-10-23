package com.example.bgame.model.internal.comments.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequest {
    private String description;
    private Long superiorCommentId;
}
