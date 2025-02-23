package com.misc.note.account.service.impl;

import com.google.common.base.Strings;
import com.misc.note.account.entity.AccountRequest;
import com.misc.note.common.constant.AccountConstant;
import com.misc.note.common.domain.AccountVO;
import com.misc.note.common.exception.BizException;
import com.misc.note.common.provider.UploadProvider;
import com.misc.note.common.resp.ResultEnum;
import com.misc.note.common.util.CommonUtil;
import com.misc.note.common.util.SmsUtil;
import com.misc.note.common.util.UploadUtil;
import com.misc.note.common.utils.RedisUtil;
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
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SmsUtil smsUtil;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public void generateCode(String contact) {
        String code = CommonUtil.randomString(6, 0);
        smsUtil.sendSms(1, contact, code, "5");
        redisUtil.set(contact, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void register(AccountRequest accountRequest) {
        log.info("注册流程开始：入参：{}", accountRequest);
        Account account = checkAndComplete(accountRequest);
        account.setAccountCode(CommonUtil.randomString(AccountConstant.DEFAULT_NOTE_CODE_LENGTH, AccountConstant.NUMBER_TYPE));
        account.setAccountStatus(AccountConstant.ACCOUNT_STATUS_NORMAL);
        account.setCreateTime(LocalDateTime.now());
        account.setCreateName(account.getAccountCode());
        save(account);
        String contact = Strings.isNullOrEmpty(accountRequest.getPhoneNumber()) ? account.getEmail() : account.getPhoneNumber();
        log.info("注册流程结束, 用户: 【{}】 注册成功! 联系方式：{}", account.getNickName(), contact);
    }

    private Account checkAndComplete(AccountRequest accountRequest){
        String phoneNumber = accountRequest.getPhoneNumber();
        if (Strings.isNullOrEmpty(accountRequest.getPhoneNumber())){
            log.error("手机号为空！");
            throw new BizException(ResultEnum.PHONE_NUMBER_EMPTY);
        }
        checkVerifyCode(phoneNumber, accountRequest.getVerifyCode());
        Account account = new Account();
        BeanUtils.copyProperties(accountRequest, account);
        if(Strings.isNullOrEmpty(accountRequest.getNickName())){
            log.warn("用户未设置昵称，随机生成12位数字字母组合昵称");
            String randomNickName = CommonUtil.randomString(AccountConstant.DEFAULT_NICK_NAME_LENGTH, AccountConstant.MIXED_TYPE);
            account.setNickName(randomNickName);
        }

        if(phoneNumberDuplicated(phoneNumber)){
            log.error("手机号：{}已被注册！", phoneNumber);
            throw new BizException(ResultEnum.PHONE_NUMBER_REGISTERED);
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

    private void checkVerifyCode(String phoneNumber, String verifyCode){
        String redisVerifyCode = (String) redisUtil.get(phoneNumber);
        if (Strings.isNullOrEmpty(redisVerifyCode)){
            log.error("验证码已过期，请重新获取验证码！");
            throw new BizException(ResultEnum.CAPTCHA_EMPTY);
        }
        if (!redisVerifyCode.equals(verifyCode)){
            log.error("验证码错误，请重新输入！");
            throw new BizException(ResultEnum.CAPTCHA_ERROR);
        }
    }

    private boolean phoneNumberDuplicated(String phoneNumber){
        QueryWrapper wrapper = QueryWrapper.create().where(ACCOUNT.PHONE_NUMBER.ge(phoneNumber));
        return count(wrapper) > 0;
    }

    @Override
    public AccountVO getAccountByContact(String phoneNumber) {
        log.info("根据手机号查询账户信息，入参：{}", phoneNumber);
        if (Strings.isNullOrEmpty(phoneNumber)){
            log.error("联系方式为空，请传入正确的手机号！");
            throw new BizException(ResultEnum.PHONE_NUMBER_EMPTY);
        }
        QueryWrapper wrapper = QueryWrapper.create().where(ACCOUNT.PHONE_NUMBER.eq(phoneNumber));
        Account account = getOne(wrapper);
        if (ObjectUtils.isEmpty(account)){
            log.error("没有查到手机号为【{}】的信息，请检查输入是否正确!", phoneNumber);
            throw new BizException(ResultEnum.ACCOUNT_NOT_EXISTS);
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
