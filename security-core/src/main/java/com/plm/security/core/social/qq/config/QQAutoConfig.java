package com.plm.security.core.social.qq.config;

import com.plm.security.core.properties.QQProperties;
import com.plm.security.core.properties.SecurityProperties;
import com.plm.security.core.social.qq.connection.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * .
 *
 * @author : cwh
 * 2019/7/5 0005
 * description ：
 */
@Configuration
@ConditionalOnProperty(prefix = "plm.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Resource
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties properties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(properties.getProviderId(), properties.getAppId(), properties.getAppSecret());
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
}
