package com.plm.security.core.controller;

import com.plm.security.core.validation.code.ValidationCodeProcessorHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : cwh
 * 2019/6/11 0011
 * description ：
 */
@RestController
public class ValidationCodeController {

    @Autowired
    private ValidationCodeProcessorHolder validationCodeProcessorHolder;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validationCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

}
