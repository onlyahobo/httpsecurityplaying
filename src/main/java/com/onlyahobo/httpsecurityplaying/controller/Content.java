package com.onlyahobo.httpsecurityplaying.controller;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Copyright (c) Asseco Business Solutions S.A. All rights reserved.
 */

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Content {

    @NotNull
    private String name;

}
