package com.sparta.springassignment4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class BlogSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) {
        // h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/user/**","/post/**").permitAll()
                // 그 외 어떤 요청이든 '인증'
                .anyRequest().authenticated()
                .and()
                // 로그인 기능 허용
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/")
//                .failureUrl("/user/login?error=true")
                .failureHandler(failureHandler())
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/");

    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthFailureHandler();
    }


    public class loginCheckFilter extends GenericFilterBean {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {

            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
