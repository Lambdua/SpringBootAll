package com.lt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author liangtao
 * @description
 * @date 2021年07月22 16:59
 **/
@EnableWebSecurity
@Configuration
//确保 WebSecurityConfigurerAdapter在ResourceConfig之前执行
//@Order()
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //开启ant模式匹配路由
                .authorizeRequests()
                //允许所有
                .antMatchers("/").permitAll()
                //访问/user/getUserList路由的带有ADMIN的的任何请求方式都要认证
                .antMatchers("/user/getUserList").hasAnyRole("ADMIN").anyRequest()
                .authenticated()
                //登录请求放行
                .and().formLogin().permitAll()
                //推出请求放行
                .and().logout().permitAll();
        http.csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        //基于内存的认证方式
        authenticationMgr.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                //密码123456
                .withUser("admin").password("$2a$10$r9i69zynZbxFmf/zgBacNOP3U8FOC5a00rFMbIvaZNxMvqZf7ax0q")
                .authorities("ROLE_ADMIN");
    }

}
