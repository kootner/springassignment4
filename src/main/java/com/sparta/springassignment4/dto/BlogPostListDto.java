package com.sparta.springassignment4.dto;

import lombok.Getter;

@Getter
public class BlogPostListDto {

    private String title;
    private String username;

    public BlogPostListDto(String title, String username) {
        this.title = title;
        this.username = username;
    }


}
