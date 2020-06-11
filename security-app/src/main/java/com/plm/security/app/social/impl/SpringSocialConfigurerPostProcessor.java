package com.plm.security.app.social.impl;

import com.plm.security.core.social.PlmSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/8/27 0027
 * description ：
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "plmSocialSecurityConfig")) {
            PlmSpringSocialConfigurer configurer = (PlmSpringSocialConfigurer) bean;
            configurer.signupUrl("/social/signUp");
        }

        return bean;
    }
}
