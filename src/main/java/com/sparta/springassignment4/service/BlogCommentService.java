package com.sparta.springassignment4.service;

import com.sparta.springassignment4.dto.BlogCommentSaveDto;
import com.sparta.springassignment4.entity.BlogComment;
import com.sparta.springassignment4.repository.BlogCommentRepository;
import com.sparta.springassignment4.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@AllArgsConstructor
@Service
public class BlogCommentService {

    private final BlogCommentRepository blogCommentRepository;


    @Transactional
    public void commentUpdate(Long id, BlogCommentSaveDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BlogComment blogComment = blogCommentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (blogComment.getUserId().equals(userDetails.getUserInfo().getId()))
            blogComment.update(requestDto);
    }


    @Transactional     // 자동으로 커밋 해줌
    public void commentDelete(Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {        //   id랑 정보가 필요해서 Dto로 정보를 받아옴
        BlogComment blogComment = blogCommentRepository.findById(id).orElseThrow(        // id를 기준으로 정보를 찾고 없으면
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")    // 오류 발생을 일으킴킴
        );
        if (blogComment.getUserId().equals(userDetails.getUserInfo().getId()))
            blogCommentRepository.deleteById(id);
    }

}
