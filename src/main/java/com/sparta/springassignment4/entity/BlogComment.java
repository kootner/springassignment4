package com.sparta.springassignment4.entity;

import com.sparta.springassignment4.dto.BlogCommentSaveDto;
import com.sparta.springassignment4.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter     // 정보 가져오기
@Entity     // 테이블과 연계해서 스프링에게 알림?
@NoArgsConstructor  //기본 생성자 생성
public class BlogComment extends Timestamped {

    @Id     // PK 사용
    @GeneratedValue(strategy = GenerationType.AUTO) // id값 자동증가
    private Long id; // id 변수 설정

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private Long userId;

    public BlogComment(String comments, String username, Long postId, Long userId) {
        this.comments = comments;
        this.username = username;
        this.postId = postId;
        this.userId = userId;
    }

    public void CommentSave(BlogCommentSaveDto requestDto) {
        this.comments = requestDto.getComments();
    }

    public void CommentSave(UserDetailsImpl userDetails) {
        this.username = userDetails.getUsername();
        this.userId = userDetails.getUserInfo().getId();
    }

    public void update (BlogCommentSaveDto requestDto) {
        this.comments = requestDto.getComments();
    }

}
