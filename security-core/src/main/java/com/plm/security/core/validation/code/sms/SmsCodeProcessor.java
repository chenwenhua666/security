package com.plm.security.core.validation.code.sms;

import com.plm.security.core.properties.SecurityConstants;
import com.plm.security.core.validation.code.ValidationCode;
import com.plm.security.core.validation.code.impl.AbstractValidationCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : cwh
 * 2019/6/13 0013
 * description ：
 */
@Component("smsValidationCodeProcessor")
public class SmsCodeProcessor extends AbstractValidationCodeProcessor<ValidationCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidationCode smsCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(phoneNumber, smsCode.getCode());
    }
}
