package com.sparta.springassignment4.entity;

import com.sparta.springassignment4.dto.BlogPostSaveDto;
import com.sparta.springassignment4.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter     // 정보 가져오기
@Entity     // 테이블과 연계해서 스프링에게 알림?
@NoArgsConstructor  //기본 생성자 생성
public class BlogPost extends Timestamped{

    @Id     // PK 사용
    @GeneratedValue(strategy = GenerationType.AUTO) // id값 자동증가
    private Long id; // id 변수 설정

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long userId;


    public BlogPost(String title, String contents, String username, Long userId){
        this.title = title;
        this.contents = contents;
        this.username = username;
        this.userId = userId;
    }

    public BlogPost(BlogPostSaveDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
        this.userId = requestDto.getUserId();
    }


    public void update(BlogPostSaveDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void userdata(UserDetailsImpl userDetails) {
        this.username = userDetails.getUsername();
        this.userId = userDetails.getUserInfo().getId();
    }

}
