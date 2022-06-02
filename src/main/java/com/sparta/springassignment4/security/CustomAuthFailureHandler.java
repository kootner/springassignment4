package com.sparta.springassignment4.security;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import org.springframework.security.core.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException)
            errorMessage = "닉네임 또는 패스워드를 확인해주세요.";
        else
            errorMessage = "알수 없는 이유로 로그인에 실패하였습니다. 관리자에게 문의하세요.";


        response.setContentType("application/text; charset=utf8");
        PrintWriter out = response.getWriter();
        out.print(errorMessage);
        out.flush();
    }
}