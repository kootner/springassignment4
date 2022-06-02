package com.sparta.springassignment4.controller;

import com.sparta.springassignment4.dto.BlogCommentSaveDto;
import com.sparta.springassignment4.entity.BlogComment;
import com.sparta.springassignment4.repository.BlogCommentRepository;
import com.sparta.springassignment4.security.UserDetailsImpl;
import com.sparta.springassignment4.service.BlogCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class BlogCommentController {

    private final BlogCommentRepository blogCommentRepository;
    private final BlogCommentService blogCommentService;

    @PostMapping("/post/content/{id}/comment/save")
    public BlogComment blogCommentSave(@PathVariable Long id, @RequestBody BlogCommentSaveDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null)
            throw new IllegalArgumentException("로그인이 필요합니다.");
        if (requestDto.getComments() == null) {
            throw new IllegalArgumentException("댓글 내용을 입력해주세요");
        } else {
            BlogComment blogComment = new BlogComment();
            blogComment.CommentSave(requestDto);
            blogComment.CommentSave(userDetails);
            blogComment.setPostId(id);
            return blogCommentRepository.save(blogComment);
        }
    }


    @PutMapping("/post/content/comment/update/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody BlogCommentSaveDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null)
            throw new IllegalArgumentException("로그인이 필요합니다.");
        blogCommentService.commentUpdate(id, requestDto, userDetails);
        return id;
    }


    @DeleteMapping("/post/content/comment/delete/{id}")
    public Long deleteComment(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null)
            throw new IllegalArgumentException("로그인이 필요합니다.");
        blogCommentService.commentDelete(id, userDetails);
        return id;
    }

}


