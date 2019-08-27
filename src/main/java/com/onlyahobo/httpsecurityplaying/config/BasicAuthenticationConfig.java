package com.onlyahobo.httpsecurityplaying.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class BasicAuthenticationConfig {

    private final String username = "login";

    private final String password = "pass";

    private final ObjectPostProcessor<Object> objectPostProcessor;

    @Autowired
    public BasicAuthenticationConfig(final ObjectPostProcessor<Object> objectPostProcessor) {
        this.objectPostProcessor = objectPostProcessor;
    }

    @Bean
    public BasicAuthenticationFilter customBasicAuthenticationFilter() throws Exception {
        final AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        final PasswordEncoder encoder = new BCryptPasswordEncoder();
        builder.inMemoryAuthentication().passwordEncoder(encoder).withUser(username).password(encoder.encode(password)).roles("BASIC_USER");
        return new BasicAuthenticationFilter(builder.build(), new BasicAuthenticationEntryPoint());
    }

}
