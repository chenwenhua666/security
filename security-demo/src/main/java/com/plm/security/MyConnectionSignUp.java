package com.plm.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author : cwh
 * 2019/7/15 0015
 * description ：
 */
@Component
public class MyConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        // 创建用户返回唯一标识
        return connection.getDisplayName();
    }
}
