package com.plm.security.app.validation.code.impl;

import com.plm.security.core.validation.code.ValidationCode;
import com.plm.security.core.validation.code.ValidationCodeException;
import com.plm.security.core.validation.code.ValidationCodeRepository;
import com.plm.security.core.validation.code.ValidationCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author : cwh
 * 2019/8/23 0023
 * description ：
 */
@Component
public class RedisValidationCodeRepository implements ValidationCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidationCode code, ValidationCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
    }

    @Override
    public ValidationCode get(ServletWebRequest request, ValidationCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, type));
        if (value == null) {
            return null;
        }
        return (ValidationCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidationCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    private String buildKey(ServletWebRequest request, ValidationCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidationCodeException("请求头需要携带deviceId");
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }


}
