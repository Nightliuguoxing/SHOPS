package top.lgx.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.gson.Gson;
import com.aliyuncs.dysmsapi.model.v20170525.*;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-18 21:55
 * @Description: 阿里云短信配置
 */
public class SMSConfig {

    public static void main(String[] args) throws ClientException {

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<your-access-key-id>", "<your-access-key-secret>");
        DefaultProfile.addEndpoint("cn-hangzhou","cn-hangzhou","Dysmsapi","dysmsapi.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
        request.setSignName("阿里云短信测试");
        request.setTemplateCode("<your-sms-template-id>");
        request.setPhoneNumbers("<Phone Number>");
        request.setTemplateParam("<your-json-data>");

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}
