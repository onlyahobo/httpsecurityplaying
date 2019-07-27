package com.onlyahobo.httpsecurityplaying.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@RestController
@Slf4j
public class CsrfController {

    @PostMapping("/post")
    public final void sendContent(@RequestBody @Valid final Content content) {
        log.info(content.toString());
    }

}
