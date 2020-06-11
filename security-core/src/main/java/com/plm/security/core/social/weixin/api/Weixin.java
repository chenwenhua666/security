package com.plm.security.core.social.weixin.api;

/**
 * @author : cwh
 * 2019/7/16 0016
 * description ：
 */
public interface Weixin {
    WeixinUserInfo getUserInfo(String openId);
}
