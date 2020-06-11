package com.plm.security.core.validation.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
public class ValidationCodeException extends AuthenticationException {

    public ValidationCodeException(String msg) {
        super(msg);
    }
}
