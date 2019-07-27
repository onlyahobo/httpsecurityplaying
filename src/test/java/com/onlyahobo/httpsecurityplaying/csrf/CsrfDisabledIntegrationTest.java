package com.onlyahobo.httpsecurityplaying.csrf;

import com.onlyahobo.httpsecurityplaying.csrf.config.SecurityWithoutCsrfConfig;
import com.onlyahobo.httpsecurityplaying.csrf.config.WebConfig;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@ContextConfiguration(classes = { SecurityWithoutCsrfConfig.class, WebConfig.class })
public class CsrfDisabledIntegrationTest extends CsrfAbstractIntegrationTest {

    @Test
    public void givenNoValidCredentialsShouldNotAuthorize() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void givenValidCredentialsShouldAuthorize() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent())
                //.with(testUser())
                .with(httpBasic("login", "pass"))
        ).andExpect(status().isOk());
    }
}
