package com.onlyahobo.httpsecurityplaying.controller;

import lombok.*;

import javax.validation.constraints.NotNull;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Content {

    @NotNull
    private String name;

}
