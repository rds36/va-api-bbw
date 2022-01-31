package com.rds.vaapibbw.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    public AppSecurityConfig(){

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/virtual_account/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/payment").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll();
        super.configure(http);
    }
}
