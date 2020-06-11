package com.plm.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : cwh
 * 2019/6/4 0004
 * description ：
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名={}", username);
        return new User(username, passwordEncoder.encode("1"), true,
                true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        log.info("用户名={}", userId);
        return new SocialUser(userId, passwordEncoder.encode("1"), true,
                true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN,ROLE_USER"));

    }
}
