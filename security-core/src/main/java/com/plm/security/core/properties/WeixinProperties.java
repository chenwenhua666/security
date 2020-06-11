package com.plm.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author : cwh
 * 2019/7/16 0016
 * description ：
 */
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
