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
    public void givenNoValidCredentialsAndValidCsrfTokenShouldNotAuthorize() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent()).with(csrf())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void givenNoValidCredentialsAndNoValidCsrfTokenShouldForbid() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent())
        ).andExpect(status().isForbidden());
    }

    @Test
    public void givenValidCredentialsButInvalidCsrfTokenShouldForbid() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent())
                //.with(testUser())
                .with(httpBasic("login", "pass"))
        ).andExpect(status().isForbidden());
    }

    @Test
    public void givenBothValidCredentialsAndCsrfTokenShouldBeOk() throws Exception {
        mvc.perform(
            post("/post").contentType(MediaType.APPLICATION_JSON)
                .content(createContent())
                //.with(testUser())
                .with(httpBasic("login", "pass")).with(csrf())
        ).andExpect(status().isOk());
    }
}