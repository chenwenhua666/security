package com.plm.security.core.social.weixin.config;

import com.plm.security.core.properties.SecurityProperties;
import com.plm.security.core.properties.WeixinProperties;
import com.plm.security.core.social.PlmConnectedView;
import com.plm.security.core.social.weixin.connection.WeixinConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author : cwh
 * 2019/7/16 0016
 * description ：
 */
@Configuration
@ConditionalOnProperty(prefix = "plm.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

    @Resource
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();
        return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("plm_");
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean({"connect/weixinConnected", "connect/weixinConnect"})
    @ConditionalOnMissingBean(name = "weixinConnectedView")
    public View weixinConnectedView() {
        return new PlmConnectedView();
    }

}
