package com.plm.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : cwh
 * 2019/6/6 0006
 * description ：
 */
@ConfigurationProperties(prefix = "plm.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private ValidationCodeProperties code = new ValidationCodeProperties();

    private SocialProperties social = new SocialProperties();

    private Oanth2Properties oauth2 = new Oanth2Properties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidationCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidationCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public Oanth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(Oanth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
