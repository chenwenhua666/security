package com.plm.security.app.config;

import com.plm.security.app.social.openid.OpenIdAuthenticationSecurityConfig;
import com.plm.security.core.authentication.phone.SmsCodeAuthenticationSecurityConfig;
import com.plm.security.core.authorize.AuthorizeConfigManager;
import com.plm.security.core.properties.SecurityConstants;
import com.plm.security.core.properties.SecurityProperties;
import com.plm.security.core.validation.code.ValidationCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;

/**
 * @author : cwh
 * 2019/8/15 0015
 * description ：
 */
@Configuration
@EnableResourceServer
public class PlmResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidationCodeSecurityConfig validationCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer plmSocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successForwardUrl("/index.html")
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
        http//.apply(validationCodeSecurityConfig)
                //.and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(plmSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }


}
