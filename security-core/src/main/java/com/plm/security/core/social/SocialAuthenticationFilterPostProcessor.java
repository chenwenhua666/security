package com.plm.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author : cwh
 * 2019/8/27 0027
 * description ：
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
