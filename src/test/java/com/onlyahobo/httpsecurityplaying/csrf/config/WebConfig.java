package com.onlyahobo.httpsecurityplaying.csrf.config;

import com.onlyahobo.httpsecurityplaying.config.BasicAuthenticationConfig;
import com.onlyahobo.httpsecurityplaying.controller.CsrfController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { BasicAuthenticationConfig.class, CsrfController.class })
public class WebConfig implements WebMvcConfigurer {

}
