package com.plm.security.core.validation.code.sms;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.plm.security.core.validation.code.ValidationCodeException;

/**
 * @author : cwh
 * 2019/6/12 0012
 * description ：
 */

public class DefaultSmsCodeSenderImpl implements SmsCodeSender {

    private static final String accessKeyId = "";
    private static final String accessSecret = "";

    @Override
    public void send(String phoneNumber, String code) {
        System.out.println("发送验证信息phoneNumber = [" + phoneNumber + "], code = [" + code + "]");

        DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "CARRY");
        request.putQueryParameter("TemplateCode", "SMS_167964224");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            throw new ValidationCodeException(e.getMessage());
        } catch (ClientException e) {
            throw new ValidationCodeException(e.getMessage());
        }


    }
}
