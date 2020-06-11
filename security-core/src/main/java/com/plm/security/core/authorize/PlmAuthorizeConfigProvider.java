package com.plm.security.core.authorize;

import com.plm.security.core.properties.SecurityConstants;
import com.plm.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/9/11 0011
 * description ：
 */
@Component
@Order(Integer.MIN_VALUE)
public class PlmAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_VALIDATION_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                securityProperties.getBrowser().getSignOutUrl(),
                "/user/regist", "/index.html")
                .permitAll();
    }
}
