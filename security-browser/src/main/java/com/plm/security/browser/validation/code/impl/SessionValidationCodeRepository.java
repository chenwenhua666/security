package com.plm.security.browser.validation.code.impl;

import com.plm.security.core.validation.code.ValidationCode;
import com.plm.security.core.validation.code.ValidationCodeRepository;
import com.plm.security.core.validation.code.ValidationCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : cwh
 * 2019/8/23 0023
 * description ：
 */
@Component
public class SessionValidationCodeRepository implements ValidationCodeRepository {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidationCode validationCode, ValidationCodeType validationCodeType) {
        ValidationCode code = new ValidationCode(validationCode.getCode(), validationCode.getExpireTime());
        sessionStrategy.setAttribute(request, getSessionKey(request, validationCodeType), code);
    }

    @Override
    public ValidationCode get(ServletWebRequest request, ValidationCodeType validationCodeType) {
        return (ValidationCode) sessionStrategy.getAttribute(request, getSessionKey(request, validationCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidationCodeType validationCodeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, validationCodeType));
    }

    /**
     * 构建验证码放入session时的key
     *
     * @param request
     * @return
     */
    private String getSessionKey(ServletWebRequest request, ValidationCodeType validationCodeType) {
        return SESSION_KEY_PREFIX + validationCodeType.toString().toUpperCase();
    }
}
