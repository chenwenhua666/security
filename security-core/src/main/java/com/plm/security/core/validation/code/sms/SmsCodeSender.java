package com.plm.security.core.validation.code.sms;

/**
 * @author : cwh
 * 2019/6/12 0012
 * description ：
 */
public interface SmsCodeSender {

    void send(String phoneNumber, String code);
}
