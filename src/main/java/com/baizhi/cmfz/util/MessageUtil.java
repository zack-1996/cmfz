package com.baizhi.cmfz.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * Created by HIAPAD on 2019/12/4.
 */
public class MessageUtil {
    public static void sendMessage(String phone,String code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FvmAPWjE9UF8652F7Zr", "VyQG9amIZABUEAHaCftYhV1rAb2gCi");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "pdjrmcgsfc");
        request.putQueryParameter("TemplateCode", "SMS_179600371");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
