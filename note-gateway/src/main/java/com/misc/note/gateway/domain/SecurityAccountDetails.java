package com.misc.note.gateway.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class SecurityAccountDetails extends User {

    private Long accountId;

    public SecurityAccountDetails(long accountId, String accountName, String password,
                                  Collection<? extends GrantedAuthority> authorities) {
        super(accountName, password, authorities);
        this.accountId = accountId;
    }

}
