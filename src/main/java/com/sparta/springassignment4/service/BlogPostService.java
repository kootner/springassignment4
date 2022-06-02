package com.sparta.springassignment4.service;

import com.sparta.springassignment4.dto.BlogCommentListDto;
import com.sparta.springassignment4.dto.BlogPostContentDto;
import com.sparta.springassignment4.dto.BlogPostSaveDto;
import com.sparta.springassignment4.entity.BlogPost;
import com.sparta.springassignment4.repository.BlogCommentRepository;
import com.sparta.springassignment4.repository.BlogPostRepository;
import com.sparta.springassignment4.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;
    private final BlogCommentRepository blogCommentRepository;

    //
    public BlogPostContentDto blogPostContents(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(        // id를 기준으로 정보를 찾고 없으면
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")    // 오류 발생을 일으킴킴
        );
        List<BlogCommentListDto> commentListDto = blogCommentRepository.findAllByPostIdOrderByCreatedAtDesc(id);
        BlogPostContentDto dto = new BlogPostContentDto(blogPost);
        dto.setCommentListDto(commentListDto);
        return dto;
    }


    @Transactional     // 자동으로 커밋 해줌
    public void postUpdate(Long id, BlogPostSaveDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {        //   id랑 정보가 필요해서 Dto로 정보를 받아옴
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(        // id를 기준으로 정보를 찾고 없으면
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")    // 오류 발생을 일으킴킴
        );

        if (blogPost.getUserId().equals(userDetails.getUserInfo().getId()))
            blogPost.update(requestDto);
    }

    @Transactional      // 자동으로 커밋 해줌
    public void postDelete(Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {        //   id랑 정보가 필요해서 Dto로 정보를 받아옴
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(        // id를 기준으로 정보를 찾고 없으면
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")    // 오류 발생을 일으킴킴
        );
        if (blogPost.getUserId().equals(userDetails.getUserInfo().getId()))
            blogPostRepository.deleteById(id);
    }

}
