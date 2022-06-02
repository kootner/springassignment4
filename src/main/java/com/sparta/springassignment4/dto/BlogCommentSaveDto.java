package com.sparta.springassignment4.dto;

import lombok.Getter;

@Getter
public class BlogCommentSaveDto {
    private String comments;
    private String username;
    private Long postId;
    private Long userId;
}
