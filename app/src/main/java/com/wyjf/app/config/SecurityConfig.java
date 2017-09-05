package com.wyjf.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.sql.ResultSet;

@Configuration
@EnableGlobalAuthentication
@ImportResource({"classpath:applicationContext-security.xml"})
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter {

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
                        AuthorityUtils.createAuthorityList("ROLE_ADMIN")
                );
        return username -> jdbcTemplate.queryForObject(
                "SELECT * from admin where name = ?", userRowMapper, username);
    }

	/*
    @Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService);
	}
	*/
}

