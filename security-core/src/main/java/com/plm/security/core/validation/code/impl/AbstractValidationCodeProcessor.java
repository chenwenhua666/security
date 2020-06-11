package com.plm.security.core.validation.code.impl;

import com.plm.security.core.validation.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author : cwh
 * 2019/6/13 0013
 * description ：
 */
public abstract class AbstractValidationCodeProcessor<T extends ValidationCode> implements ValidationCodeProcessor {

    /**
     * 收集系统中所有的 {@link ValidationCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidationCodeGenerator> validationCodeGenerators;

    @Autowired
    private ValidationCodeRepository validationCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        T validationCode = generate(request);
        save(request, validationCode);
        send(request, validationCode);
    }

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    private T generate(ServletWebRequest request) {
        String type = getValidationCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidationCodeGenerator.class.getSimpleName();
        ValidationCodeGenerator validationCodeGenerator = validationCodeGenerators.get(generatorName);
        if (validationCodeGenerator == null) {
            throw new ValidationCodeException("验证码生成器" + generatorName + "不存在");
        }
        return (T) validationCodeGenerator.generate(request);
    }

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidationCodeType getValidationCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor");
        return ValidationCodeType.valueOf(type.toUpperCase());
    }

    /**
     * 保存验证码
     *
     * @param request
     * @param validationCode
     */
    private void save(ServletWebRequest request, T validationCode) {
        ValidationCode code = new ValidationCode(validationCode.getCode(), validationCode.getExpireTime());
        //sessionStrategy.setAttribute(request, getSessionKey(request), code);
        validationCodeRepository.save(request, code, getValidationCodeType(request));
    }

    protected abstract void send(ServletWebRequest request, T validationCode) throws Exception;

    @Override
    public void validate(ServletWebRequest request) {
        ValidationCodeType codeType = getValidationCodeType(request);
        //String sessionKey = getSessionKey(request);
        T codeInSession = (T) validationCodeRepository.get(request, codeType);
        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidationCodeException("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidationCodeException(codeInRequest + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidationCodeException(codeType + "验证码不存在");
        }

        if (codeInSession.isExpired()) {
            //sessionStrategy.removeAttribute(request, sessionKey);
            validationCodeRepository.remove(request, codeType);
            throw new ValidationCodeException(codeType + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidationCodeException(codeType + "验证码输入有误");
        }
        // sessionStrategy.removeAttribute(request, sessionKey);
        validationCodeRepository.remove(request, codeType);
    }
}
