package com.misc.note.common.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountVO {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 账户编号
     */
    private String accountCode;

    /**
     * 账户昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 性别：0-男，1-女
     */
    private String gender;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 账户状态: 0-正常，1-冻结
     */
    private String accountStatus;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    private String updateName;
}
