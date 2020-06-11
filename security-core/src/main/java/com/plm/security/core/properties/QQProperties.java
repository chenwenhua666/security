package com.plm.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author : cwh
 * 2019/7/5 0005
 * description ：
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
