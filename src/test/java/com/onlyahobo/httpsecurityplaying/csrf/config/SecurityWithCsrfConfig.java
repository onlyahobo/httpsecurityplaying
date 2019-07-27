package com.onlyahobo.httpsecurityplaying.csrf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityWithCsrfConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthenticationFilter basicAuthenticationFilter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.requestMatchers()
            .and().authorizeRequests().anyRequest().authenticated()
            .and().httpBasic()
            .and().csrf()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(basicAuthenticationFilter, BasicAuthenticationFilter.class);
    }

}
