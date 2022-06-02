package com.sparta.springassignment4.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor// 기본 생성자 만들어줌
@Entity // DB 테이블 역할
public class UserInfo {

    // ID가 자동으로 생성 및 증가
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable: null 허용여부
    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
