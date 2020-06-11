package com.plm.security.core.validation.code;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : cwh
 * 2019/6/9 0009
 * description ：
 */
public class ValidationCode implements Serializable {

    private static final long serialVersionUID = -7731375489857492265L;

    private String code;
    private LocalDateTime expireTime;

    public ValidationCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidationCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
