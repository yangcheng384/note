package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.AccountFans;
import com.misc.note.account.mapper.AccountFansMapper;
import com.misc.note.account.service.AccountFansService;
import org.springframework.stereotype.Service;

/**
 * 账户粉丝表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class AccountFansServiceImpl extends ServiceImpl<AccountFansMapper, AccountFans>  implements AccountFansService{

}
