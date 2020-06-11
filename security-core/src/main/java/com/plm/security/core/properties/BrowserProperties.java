package com.plm.security.core.properties;

import static com.plm.security.core.properties.LoginType.JSON;

/**
 * @author : cwh
 * 2019/6/6 0006
 * description ：
 */
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    private String signUpUrl = "/signUp.html";

    private String signOutUrl;

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = JSON;

    private int rememberSeconds = 60;

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberSeconds() {
        return rememberSeconds;
    }

    public void setRememberSeconds(int rememberSeconds) {
        this.rememberSeconds = rememberSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }
}
