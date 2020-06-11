package com.plm.security.core.validation.code;

import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
public interface ValidationCodeGenerator {

    ValidationCode generate(ServletWebRequest request);
}
