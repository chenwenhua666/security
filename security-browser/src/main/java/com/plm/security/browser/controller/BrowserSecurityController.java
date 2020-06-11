package com.plm.security.browser.controller;

import com.plm.security.core.model.SocialUserInfo;
import com.plm.security.core.properties.SecurityConstants;
import com.plm.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : cwh
 * 2019/6/5 0005
 * description ：
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public ResponseEntity visit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：" + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        Map<String, String> map = new HashMap<>();
        map.put("msg", "访问需要身份认证");
        return new ResponseEntity<Map>(map, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setNickname(connection.getDisplayName());
        socialUserInfo.setHeadimg(connection.getImageUrl());
        return socialUserInfo;
    }

    @GetMapping("/session/invalid")
    public ResponseEntity sessionInvalid() {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "session失效，登录过期");
        return new ResponseEntity<Map>(map, HttpStatus.UNAUTHORIZED);
    }

}
