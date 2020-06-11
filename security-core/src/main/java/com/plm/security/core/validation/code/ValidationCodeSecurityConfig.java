package com.plm.security.core.validation.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/6/19 0019
 * description ：
 */
@Component
public class ValidationCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private ValidationCodeFilter validationCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validationCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }

}
