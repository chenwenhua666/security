package com.plm.security;

import com.plm.security.core.authorize.AuthorizeConfigProvider;
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
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        /*
        config.antMatchers(HttpMethod.GET,"/user/*").hasRole("ADMIN");
        config.antMatchers("/index.html").hasRole("MANAGE");
        config.antMatchers(HttpMethod.GET,"/user/*").access("hasRole('ADMIN') and hasAnyAuthority('test')");
        */
        config.anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }
}
