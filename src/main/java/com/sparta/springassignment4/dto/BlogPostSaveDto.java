package com.sparta.springassignment4.dto;

import lombok.Getter;

@Getter
public class BlogPostSaveDto {
    private String title;
    private String contents;
    private String username;
    private Long userId;
}
