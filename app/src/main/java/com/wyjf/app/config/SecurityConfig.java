package com.wyjf.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.ResultSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
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
        return (username) ->
        {
            User user = jdbcTemplate.queryForObject("SELECT * from admin where name = ?", userRowMapper, username);
            log.info("登陆用户名:{},{}", username, user);
            return user;
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                // Spring Security should completely ignore URLs starting with /resources/
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/admin/index").hasRole("ADMIN")
                .antMatchers("/admin/foo").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
//                .antMatchers("/admin/foo").hasRole("USER")
//                .anyRequest().hasRole("USER")
                .and()
                // Possibly more configuration ...
                .formLogin().loginPage("/login").defaultSuccessUrl("/admin/index")//.successForwardUrl("/admin/index") // enable form based log in
                // set permitAll for all URLs associated with Form Login
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailsService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
	private UserDetailsService userDetailsService;
}

