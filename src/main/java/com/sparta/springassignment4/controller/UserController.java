package com.sparta.springassignment4.controller;

import com.sparta.springassignment4.dto.SignupRequestDto;
import com.sparta.springassignment4.security.UserDetailsImpl;
import com.sparta.springassignment4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RequiredArgsConstructor // 변수 선언시 생성자 주입
@RestController
public class UserController {

    private final UserService userService;

    // 회원 로그인 페이지

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@RequestBody SignupRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null)
            throw new IllegalArgumentException("이미 로그인이 되어있습니다.");
        if (Objects.equals(userService.overlapName(requestDto), "중복체크 성공")) {
            userService.registerUser(requestDto);
            return "회원가입이 완료되었습니다.";
        } else return "중복된 닉네임입니다.";

    }

    @GetMapping("/user/info")
    public UserDetailsImpl userD(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userDetails;
    }
}
