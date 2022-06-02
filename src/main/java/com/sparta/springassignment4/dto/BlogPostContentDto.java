package com.sparta.springassignment4.dto;

import com.sparta.springassignment4.entity.BlogPost;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BlogPostContentDto  {

    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private List<com.sparta.springassignment4.dto.BlogCommentListDto> commentListDto;


    public BlogPostContentDto(String title, String contents, LocalDateTime createdAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }
    public BlogPostContentDto(BlogPost blogPost) {
        this.title = blogPost.getTitle();
        this.contents = blogPost.getContents();
        this.createdAt = blogPost.getCreatedAt();
    }
}
