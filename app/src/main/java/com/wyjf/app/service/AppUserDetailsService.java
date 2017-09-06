package com.wyjf.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/9/6.
 */
public class AppUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AppUserDetailsService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        RowMapper<User> userRowMapper = (ResultSet rs, int i) ->
                new User(
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("enabled"),
                        rs.getBoolean("enabled"),
//                        AuthorityUtils.createAuthorityList("ROLE_ADMIN")
                        "root".equals(rs.getString("name")) ?
                                AuthorityUtils.createAuthorityList("ROLE_ADMIN") :
                                AuthorityUtils.createAuthorityList("ROLE_USER")
                );
        User user = jdbcTemplate.queryForObject("SELECT * from admin where name = ?", userRowMapper, username);
        log.info("登陆用户名:{},{}", username, user);
        return user;

    }
}
