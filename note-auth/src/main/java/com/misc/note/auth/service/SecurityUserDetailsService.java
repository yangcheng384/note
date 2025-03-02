package com.misc.note.auth.service;

import com.google.common.base.Strings;
import com.misc.note.common.domain.AccountVO;
import com.misc.note.auth.domain.SecurityAccountDetails;
import com.misc.note.auth.feign.AccountFeignClient;
import com.misc.note.common.resp.ResultVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

    @Resource
    private AccountFeignClient accountFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            ResultVO<AccountVO> resultVO = accountFeignClient.getAccountByPhoneNumber(username);
            AccountVO account = resultVO.getData();
            if (account == null){
                throw new UsernameNotFoundException(username);
            }
            String contact = Strings.isNullOrEmpty(account.getPhoneNumber())
                                ? account.getEmail():account.getPhoneNumber();
            return new SecurityAccountDetails(account.getId(), contact, account.getPassword(),
                          Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
