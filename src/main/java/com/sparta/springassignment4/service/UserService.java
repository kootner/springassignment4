package com.sparta.springassignment4.service;

import com.sparta.springassignment4.dto.SignupRequestDto;
import com.sparta.springassignment4.entity.UserInfo;
import com.sparta.springassignment4.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 ID 중복 확인
    public String overlapName(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        Optional<UserInfo> found = userInfoRepository.findByUsername(username);
         if(!found.isPresent()){
             return "중복체크 성공";
         }else
             return "중복된 닉네임입니다.";
    }


    public UserInfo registerUser(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        // 3자리 이상만 나오는거 찾으면 변경 예정
        if (!Pattern.matches("^[\\da-zA-Z]{3,}$", username))
            throw new IllegalArgumentException("3글자 이상 알파벳 대소문자와 숫자로만 입력하세요");

        if (password.length()<4)
            throw new IllegalArgumentException("비밀번호는 4자리 이상입니다.");

        if (password.contains(username))
            throw new IllegalArgumentException("비밀번호에 닉네임을 포함할 수 없습니다.");

        if (!password.equals(passwordCheck))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");

        // 패스워드 암호화
        String encoded_Password = passwordEncoder.encode(requestDto.getPassword());

        UserInfo userInfo = new UserInfo(username, encoded_Password);
        userInfoRepository.save(userInfo);
        return userInfo;
    }
}
