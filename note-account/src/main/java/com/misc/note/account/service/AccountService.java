package com.misc.note.account.service;

import com.misc.note.account.entity.AccountRequest;
import com.misc.note.common.domain.AccountVO;
import com.mybatisflex.core.service.IService;
import com.misc.note.account.entity.Account;

/**
 * 账户表 服务层。
 *
 * @author 11606
 * @since 2025-02-12
 */
public interface AccountService extends IService<Account> {

    void register(AccountRequest accountRequest);

    AccountVO getAccountByContact(String contact);
}
