package com.sparta.springassignment4.security;

import com.sparta.springassignment4.entity.UserInfo;
import com.sparta.springassignment4.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException
                        ("Can't find " + username));
        return new UserDetailsImpl(userInfo);
    }
}
