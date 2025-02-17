package com.misc.note.common.resp;

import lombok.Getter;

@Getter
public enum ResultEnum {

    NORMAL(0, "normal"),
    SUCCESS(200, "success"),
    FAILURE(500, "failure"),

    UNAUTHORIZED(401, "账户未授权!"),
    FORBIDDEN(403, "禁止访问！"),

    PHONE_OR_EMAIL_MUST_NOT_NULL(10001, "手机号和邮箱至少有一个不能为空！"),
    PHONE_NUMBER_ERROR(10002, "手机号格式不正确!"),
    PASSWORD_ERROR(10003, "密码需为长度为8-20位的数字与字母组合!"),
    INTRODUCE_TOO_LONG(10004, "简介不能超过500字"),
    EMAIL_ERROR(10005, "邮箱地址不正确!"),
    PHONE_OR_EMAIL_EMPTY(10006, "手机号或邮箱为空！"),
    EMAIL_REGISTERED(10007, "邮箱已被注册！"),
    PHONE_NUMBER_REGISTERED(10008, "手机号已被注册！"),
    PHONE_OR_EMAIL_ERROR(10009, "手机号或邮箱输入不正确!"),
    TOKEN_EMPTY(10010, "token不存在！"),
    TOKEN_INVALID(10011, "非法的token"),
    ;

    private final int code;
    private final String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
