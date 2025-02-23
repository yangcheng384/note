package com.misc.note.common.enums;

import lombok.Getter;

@Getter
public enum SmsEnums {

    OTHERS(0, "", ""),
    REGISTER(1, "SMS_479040388", "{\"code\":\"${code}\",\"time\":\"${time}\"}"),
    LOGIN(2, "SMS_479020434", "{\"code\":\"${code}\"}"),
    RESET_PASSWORD(3, "SMS_479035427", "{\"code\":\"${code}\"}"),
    UPDATE_PASSWORD(4, "SMS_479085410", "{\"code\":\"${code}\"}"),
    UPDATE_PHONE(5, "SMS_479080437", "{\"code\":\"${code}\"}")
    ;

    private final int type;

    private final String templateCode;

    private final String templateParam;

    SmsEnums(int type, String templateCode, String templateParam) {
        this.type = type;
        this.templateCode = templateCode;
        this.templateParam = templateParam;
    }

    public static SmsEnums of(int type) {
        for (SmsEnums smsEnums : SmsEnums.values()) {
            if (smsEnums.getType() == type) {
                return smsEnums;
            }
        }
        return SmsEnums.OTHERS;
    }
}
