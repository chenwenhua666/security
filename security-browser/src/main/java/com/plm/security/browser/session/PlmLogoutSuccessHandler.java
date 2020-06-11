package com.plm.security.browser.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : cwh
 * 2019/8/13 0013
 * description ：
 */
@Slf4j
public class PlmLogoutSuccessHandler implements LogoutSuccessHandler {

    private String signOutUrl;

    public PlmLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("退出登录");
        } else {
            response.sendRedirect(signOutUrl);
        }
    }
}
