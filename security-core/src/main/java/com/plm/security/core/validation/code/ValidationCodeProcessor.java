package com.plm.security.core.validation.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : cwh
 * 2019/6/12 0012
 * description ：
 */
public interface ValidationCodeProcessor {

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     */
    void validate(ServletWebRequest servletWebRequest);

}
