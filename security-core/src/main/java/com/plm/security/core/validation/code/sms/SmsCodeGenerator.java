package com.plm.security.core.validation.code.sms;

import com.plm.security.core.properties.SecurityProperties;
import com.plm.security.core.validation.code.ValidationCode;
import com.plm.security.core.validation.code.ValidationCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
@Component("smsValidationCodeGenerator")
public class SmsCodeGenerator implements ValidationCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidationCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidationCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
