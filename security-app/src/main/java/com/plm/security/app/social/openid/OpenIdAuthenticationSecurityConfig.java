package com.plm.security.app.social.openid;

import com.plm.security.core.authentication.phone.SmsCodeAuthenticationFilter;
import com.plm.security.core.authentication.phone.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/6/13 0013
 * description ：
 */
@Component
public class OpenIdAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler aPPauthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler aPPauthenticationFailureHandler;

    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        OpenIdAuthenticationFilter openIdAuthenticationFilter = new OpenIdAuthenticationFilter();
        openIdAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        openIdAuthenticationFilter.setAuthenticationSuccessHandler(aPPauthenticationSuccessHandler);
        openIdAuthenticationFilter.setAuthenticationFailureHandler(aPPauthenticationFailureHandler);

        OpenIdAuthenticationProvider openIdAuthenticationProvider = new OpenIdAuthenticationProvider();
        openIdAuthenticationProvider.setUserDetailsService(userDetailsService);
        openIdAuthenticationProvider.setUsersConnectionRepository(usersConnectionRepository);

        http.authenticationProvider(openIdAuthenticationProvider)
                .addFilterAfter(openIdAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
