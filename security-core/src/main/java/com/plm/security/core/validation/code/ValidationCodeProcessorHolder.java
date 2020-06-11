package com.plm.security.core.validation.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : cwh
 * 2019/6/18 0018
 * description ：
 */

@Component
public class ValidationCodeProcessorHolder {

    @Autowired
    private Map<String, ValidationCodeProcessor> validationCodeProcessors;

    public ValidationCodeProcessor findValidateCodeProcessor(ValidationCodeProcessor type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidationCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidationCodeProcessor.class.getSimpleName();
        ValidationCodeProcessor processor = validationCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidationCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }

}
