package com.plm.security.browser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plm.security.core.properties.LoginType;
import com.plm.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : cwh
 * 2019/6/6 0006
 * description ：
 */
@Slf4j
@Component
public class BrowserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        } else {
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }


    }
}
