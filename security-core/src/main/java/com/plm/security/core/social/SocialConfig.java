package com.plm.security.core.social;

import com.plm.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author : cwh
 * 2019/7/4 0004
 * description ：
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    /*@Resource
    private DataSource dataSource;*/

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired(required = false)
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    /*@Autowired(required = false)
    private ConnectionSignUp connectionSignUp;*/

    /*@Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("plm_");
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }*/

    @Bean
    public SpringSocialConfigurer plmSocialSecurityConfig() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        PlmSpringSocialConfigurer configurer = new PlmSpringSocialConfigurer(filterProcessesUrl);
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return configurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }

}
