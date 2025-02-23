package com.misc.note.account.service;

import com.misc.note.account.entity.AccountRequest;
import com.misc.note.common.domain.AccountVO;
import com.mybatisflex.core.service.IService;
import com.misc.note.account.entity.Account;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账户表 服务层。
 *
 * @author 11606
 * @since 2025-02-12
 */
public interface AccountService extends IService<Account> {

    void generateCode(String contact);

    void register(AccountRequest accountRequest);

    AccountVO getAccountByContact(String contact);

    String uploadFile(MultipartFile file);
}
