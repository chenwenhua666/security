package com.plm.security.core.validation.code;

import com.plm.security.core.validation.code.image.ImageCodeGenerator;
import com.plm.security.core.validation.code.sms.DefaultSmsCodeSenderImpl;
import com.plm.security.core.validation.code.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
@Configuration
public class ValidationCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageValidationCodeGenerator")
    public ValidationCodeGenerator imageValidationCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSenderImpl();
    }

}
