package com.sparta.springassignment4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlogCommentListDto {
    private String comments;
    private String username;
    private Long postId;
    private Long userId;


}
