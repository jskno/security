package com.jskno.order.web.oauth.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/oauth2/code/users-client-oidc")
public class OauthController {

    @GetMapping
    public String getCode(@RequestParam String code) {
        return code;
    }
}
