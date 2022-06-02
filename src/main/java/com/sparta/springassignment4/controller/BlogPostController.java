package com.sparta.springassignment4.controller;

import com.sparta.springassignment4.dto.BlogPostContentDto;
import com.sparta.springassignment4.dto.BlogPostListDto;
import com.sparta.springassignment4.dto.BlogPostSaveDto;
import com.sparta.springassignment4.entity.BlogPost;
import com.sparta.springassignment4.repository.BlogPostRepository;
import com.sparta.springassignment4.security.UserDetailsImpl;
import com.sparta.springassignment4.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor    // final 변수 선언시 생성자 주입
@RestController
public class BlogPostController {

    private final BlogPostRepository blogPostRepository;
    private final BlogPostService blogPostService;

    @GetMapping("/post/lists")
    public List<BlogPostListDto> blogPostList() {
        return blogPostRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/post/content/{id}")
    public BlogPostContentDto blogPostContents(@PathVariable Long id) {
        return blogPostService.blogPostContents(id);
    }

    @PostMapping("/post/save")         // 데이터 저장
    public BlogPost blogPostSave(@RequestBody BlogPostSaveDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null)
            throw new IllegalArgumentException("로그인이 필요합니다.");
        BlogPost blogPost = new BlogPost(requestDto);
        blogPost.userdata(userDetails);
        return blogPostRepository.save(blogPost);
    }

    @PutMapping("/post/update/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody BlogPostSaveDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) { // URL경로 {id}에 변수 id를 넣어주고 메개변수에 데이터를 JSON형식으로 받음
        if (userDetails == null)
            throw new IllegalArgumentException("로그인이 필요합니다.");
        blogPostService.postUpdate(id, requestDto, userDetails);     // id에 일치하는 부분에 데이터들을 변경요청받은 값으로 수정해서 DTO로 전달
        return id;
    }

    @DeleteMapping("/post/delete/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null)
            throw new IllegalArgumentException("로그인이 필요합니다.");
        blogPostService.postDelete(id, userDetails);
        return id;
    }

}
