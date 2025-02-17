package com.misc.note.account.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.misc.note.account.entity.AccountFocus;
import com.misc.note.account.mapper.AccountFocusMapper;
import com.misc.note.account.service.AccountFocusService;
import org.springframework.stereotype.Service;

/**
 * 账户关注表 服务层实现。
 *
 * @author 11606
 * @since 2025-02-12
 */
@Service
public class AccountFocusServiceImpl extends ServiceImpl<AccountFocusMapper, AccountFocus>  implements AccountFocusService{

}
