package com.plm.exception;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */
public class UserException extends RuntimeException {

    private String code;

    public UserException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
