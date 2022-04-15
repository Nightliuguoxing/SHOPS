package top.lgx.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: LGX-LUCIFER
 * @Date: 2022-04-14 11:22
 * @Description:
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    private static final Logger LOG = LoggerFactory.getLogger(SmsListener.class);

    @RabbitHandler
    public void sendSms(Map<String, String> message){
        LOG.info("手机号: " + message.get("mobile") + " | " + "短信内容: " + message.get("code"));
    }
}
