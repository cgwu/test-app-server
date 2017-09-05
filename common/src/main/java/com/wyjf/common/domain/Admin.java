package com.wyjf.common.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Administrator on 2017/9/5.
 */
public class Admin extends org.springframework.security.core.userdetails.User  {
    public Admin(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true, true, true, authorities);
    }
}
