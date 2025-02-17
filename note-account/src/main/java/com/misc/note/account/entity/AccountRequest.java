package com.misc.note.account.entity;

import com.misc.note.common.annotations.Error;
import com.misc.note.common.resp.ResultEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRequest {

    /**
     * 账户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    @Error(resultEnum = ResultEnum.PHONE_NUMBER_ERROR)
    private String phoneNumber;

    /**
     * 邮箱
     */
    @Email
    @Error(resultEnum = ResultEnum.EMAIL_ERROR)
    private String email;

    /**
     * 登录密码
     */
    @NotBlank
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")
    @Error(resultEnum = ResultEnum.PASSWORD_ERROR)
    private String password;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 简介
     */
    @Size(max = 500)
    @Error(resultEnum = ResultEnum.INTRODUCE_TOO_LONG)
    private String introduce;

    /**
     * 性别：0-男，1-女
     */
    private String gender;

    /**
     * 生日
     */
    private String birthday;
}