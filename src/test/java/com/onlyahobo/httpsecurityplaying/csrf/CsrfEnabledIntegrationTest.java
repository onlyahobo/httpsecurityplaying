package com.onlyahobo.httpsecurityplaying.csrf;

import com.onlyahobo.httpsecurityplaying.csrf.config.SecurityWithCsrfConfig;
import com.onlyahobo.httpsecurityplaying.csrf.config.WebConfig;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@ContextConfiguration(classes = { SecurityWithCsrfConfig.class, WebConfig.class })
public class CsrfEnabledIntegrationTest extends CsrfAbstractIntegrationTest {

    @Test
    public void givenNotAuth_whenAddFoo_thenUnauthorized() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent()).with(csrf())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void givenAuth_whenAddFoo_thenCreated() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent())
                //.with(testUser())
                .with(httpBasic("login", "pass")).with(csrf())
        ).andExpect(status().isOk());
    }
}