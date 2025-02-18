package com.misc.note.account.service.impl;

import com.google.common.base.Strings;
import com.misc.note.account.entity.AccountRequest;
import com.misc.note.common.constant.AccountConstant;
import com.misc.note.common.domain.AccountVO;
import com.misc.note.common.exception.BizException;
import com.misc.note.common.provider.UploadProvider;
import com.misc.note.common.resp.ResultEnum;
import com.misc.note.common.util.CommonUtil;
import com.misc.note.common.util.UploadUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.Account;
import com.misc.note.account.mapper.AccountMapper;
import com.misc.note.account.service.AccountService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

import static com.misc.note.account.entity.table.AccountTableDef.ACCOUNT;

/**
 * 账户表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>  implements AccountService{

    @Resource
    private UploadProvider uploadProvider;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public void register(AccountRequest accountRequest) {
        log.info("注册流程开始：入参：{}", accountRequest);
        Account account = check(accountRequest);
        account.setAccountCode(CommonUtil.randomString(AccountConstant.DEFAULT_NOTE_CODE_LENGTH, AccountConstant.NUMBER_TYPE));
        account.setAccountStatus(AccountConstant.ACCOUNT_STATUS_NORMAL);
        account.setCreateTime(LocalDateTime.now());
        account.setCreateName(account.getAccountCode());
        save(account);
        String contact = Strings.isNullOrEmpty(accountRequest.getPhoneNumber()) ? account.getEmail() : account.getPhoneNumber();
        log.info("注册流程结束, 用户: 【{}】 注册成功! 联系方式：{}", account.getNickName(), contact);
    }

    private Account check(AccountRequest accountRequest){
        Account account = new Account();
        BeanUtils.copyProperties(accountRequest, account);
        if(Strings.isNullOrEmpty(accountRequest.getNickName())){
            log.warn("用户未设置昵称，随机生成12位数字字母组合昵称");
            String randomNickName = CommonUtil.randomString(AccountConstant.DEFAULT_NICK_NAME_LENGTH, AccountConstant.MIXED_TYPE);
            account.setNickName(randomNickName);
        }
        String email = accountRequest.getEmail();
        String phoneNumber = accountRequest.getPhoneNumber();
        if (Strings.isNullOrEmpty(phoneNumber) && Strings.isNullOrEmpty(email)){
            log.error("手机号和邮箱至少有一个不能为空！");
            throw new BizException(ResultEnum.PHONE_OR_EMAIL_MUST_NOT_NULL);
        }else if (Strings.isNullOrEmpty(phoneNumber)){
            if(emailDuplicated(email)){
                log.error("邮箱:{}已被注册！", email);
                throw new BizException(ResultEnum.EMAIL_REGISTERED);
            }
            account.setEmail(email);
        }else {
            if(phoneNumberDuplicated(phoneNumber)){
                log.error("手机号：{}已被注册！", phoneNumber);
                throw new BizException(ResultEnum.PHONE_NUMBER_REGISTERED);
            }
            account.setPhoneNumber(phoneNumber);
        }
        String password = accountRequest.getPassword();
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        if (Strings.isNullOrEmpty(accountRequest.getAvatarUrl())){
            log.warn("用户未设置头像，使用默认头像，地址：{}", AccountConstant.DEFAULT_AVATAR_URL);
            account.setAvatarUrl(AccountConstant.DEFAULT_AVATAR_URL);
        }else{
            account.setAvatarUrl(accountRequest.getAvatarUrl());
        }
        return account;
    }

    private boolean emailDuplicated(String email){
        QueryWrapper wrapper = QueryWrapper.create().where(ACCOUNT.EMAIL.ge(email));
        return count(wrapper) > 0;
    }

    private boolean phoneNumberDuplicated(String phoneNumber){
        QueryWrapper wrapper = QueryWrapper.create().where(ACCOUNT.PHONE_NUMBER.ge(phoneNumber));
        return count(wrapper) > 0;
    }

    @Override
    public AccountVO getAccountByContact(String contact) {
        log.info("根据联系方式查询账户信息，入参：{}", contact);
        if (Strings.isNullOrEmpty(contact)){
            log.error("联系方式为空，请传入正确的手机号或者邮箱地址！");
            throw new BizException(ResultEnum.PHONE_OR_EMAIL_EMPTY);
        }
        QueryWrapper wrapper = QueryWrapper.create()
                .where(ACCOUNT.EMAIL.eq(contact)).or(ACCOUNT.PHONE_NUMBER.eq(contact));
        Account account = getOne(wrapper);
        if (ObjectUtils.isEmpty(account)){
            log.error("没有查到联系方式为【{}】的信息，请检查输入是否正确!", contact);
            throw new BizException(ResultEnum.PHONE_OR_EMAIL_ERROR);
        }
        AccountVO accountVO = new AccountVO();
        BeanUtils.copyProperties(account, accountVO);
        log.info("查询到账户信息：{}", accountVO);
        return accountVO;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()){
            log.error("上传的头像不存在！");
            throw new BizException(ResultEnum.FILE_EMPTY);
        }
        String originalFilename = file.getOriginalFilename();
        String fileName = originalFilename;
        if (!Strings.isNullOrEmpty(originalFilename)) {
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = System.nanoTime() + suffix;
        }
        File targetFile = new File(fileName);
        try {
            UploadUtil.makeBucket(bucketName);
            MinioClient minioClient = uploadProvider.getMinioClient();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(AccountConstant.FILE_UPLOAD_TARGET_PATH + fileName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getInputStream().available(), -1).build());
            log.info("上传头像【{}】成功！", fileName);
        } catch (Exception e){
            log.error("上传头像异常, {}", e.getMessage());
        }
        return targetFile.getPath();
    }
}
