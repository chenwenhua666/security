package com.plm.security.browser.config;

import com.plm.security.core.authentication.AuthenticationSecurityConfig;
import com.plm.security.core.authentication.phone.SmsCodeAuthenticationSecurityConfig;
import com.plm.security.core.authorize.AuthorizeConfigManager;
import com.plm.security.core.properties.SecurityConstants;
import com.plm.security.core.properties.SecurityProperties;
import com.plm.security.core.validation.code.ValidationCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author : cwh
 * 2019/6/4 0004
 * description ：
 */
@Configuration
public class BrowserSecurityConfig extends AuthenticationSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private DataSource dataSource;

    @Resource
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidationCodeSecurityConfig validationCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer plmSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        applyPasswordAuthenticationConfig(http);

        http.apply(validationCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(plmSocialSecurityConfig)
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                //.invalidSessionUrl("/session/invalid")
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy).and()
                .and()
                .logout()
                .logoutUrl("/signOut")
                //.logoutSuccessUrl(securityProperties.getBrowser().getLoginPage())
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                /*.authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATION_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/regist", "/index.html")
                .permitAll()
                //.antMatchers(HttpMethod.GET,"/user/*").hasRole("ADMIN")
                //.antMatchers(HttpMethod.GET,"/user/*").access("hasRole('ADMIN') and hasAnyAuthority('test')")
                .anyRequest()
                .authenticated()
                .and()*/
                .csrf().disable();
        authorizeConfigManager.config(http.authorizeRequests());
    }

}
