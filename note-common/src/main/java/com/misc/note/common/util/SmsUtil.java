package com.misc.note.common.util;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.misc.note.common.enums.SmsEnums;
import com.misc.note.common.provider.AliyunSmsProvider;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsUtil {

    @Resource
    private AliyunSmsProvider smsProvider;

    private final String SIGN_NAME = "life日常";

    public void sendSms(int type, String phoneNumber, String... params){
        try {
            Client client = smsProvider.createClient();
            SendSmsRequest request = new SendSmsRequest();
            request.setPhoneNumbers(phoneNumber);
            request.setSignName(SIGN_NAME);
            SmsEnums smsEnums = SmsEnums.of(type);
            String templateParam = smsEnums.getTemplateParam();
            templateParam = templateParam.replace("${code}", params[0]).replace("${time}", params[1]);
            request.setTemplateCode(smsEnums.getTemplateCode());
            request.setTemplateParam(templateParam);
            RuntimeOptions runtimeOptions = new RuntimeOptions();
            SendSmsResponse response = client.sendSmsWithOptions(request, runtimeOptions);
            log.info("发送结果：响应码：{}, 响应信息：{}", response.getBody().getCode(), response.getBody().getMessage());
        }  catch (Exception error) {
            TeaException e = new TeaException(error.getMessage(), error);
            if (e.getData() != null && e.getData().containsKey("Recommend")) {
                System.out.println(e.getData().get("Recommend"));
            } else {
                System.out.println("No recommendation data available.");
            }
            log.error("发送短信失败", e); // 记录完整的异常堆栈信息
        }
    }
}
