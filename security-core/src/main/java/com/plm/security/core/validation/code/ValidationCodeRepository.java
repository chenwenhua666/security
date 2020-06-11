package com.plm.security.core.validation.code;

import com.plm.security.core.validation.code.ValidationCode;
import com.plm.security.core.validation.code.ValidationCodeType;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author : cwh
 * 2019/8/23 0023
 * description ：
 */
public interface ValidationCodeRepository {

    /**
     * 保存验证码
     *
     * @param request
     * @param code
     * @param validationCodeType
     */
    void save(ServletWebRequest request, ValidationCode code, ValidationCodeType validationCodeType);

    /**
     * 获取验证码
     *
     * @param request
     * @param validationCodeType
     * @return
     */
    ValidationCode get(ServletWebRequest request, ValidationCodeType validationCodeType);

    /**
     * 移除验证码
     *
     * @param request
     * @param validationCodeType
     */
    void remove(ServletWebRequest request, ValidationCodeType validationCodeType);

}
