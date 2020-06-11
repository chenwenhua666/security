package com.plm.security.app.social.impl;

import com.plm.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/8/27 0027
 * description ：
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(appAuthenticationSuccessHandler);
    }
}
