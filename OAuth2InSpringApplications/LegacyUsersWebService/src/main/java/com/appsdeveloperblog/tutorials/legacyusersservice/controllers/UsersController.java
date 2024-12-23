package com.appsdeveloperblog.tutorials.legacyusersservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.tutorials.legacyusersservice.service.UsersService;
import com.appsdeveloperblog.tutorials.legacyusersservice.response.UserRest;
import com.appsdeveloperblog.tutorials.legacyusersservice.response.VerifyPasswordResponse;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/{userName}")
    public UserRest getUser(@PathVariable("userName") String userName) {

        return usersService.getUserDetails(userName);

    }

    @PostMapping("/{userName}/verify-password")
    public VerifyPasswordResponse verifyUserPassword(@PathVariable String userName,
            @RequestBody String password) {

        VerifyPasswordResponse returnValue = new VerifyPasswordResponse(false);

        String substring = password.substring(password.indexOf("=") + 1);
        UserRest user = usersService.getUserDetails(userName, substring);

        if (user != null) {
            returnValue.setResult(true);
        }

        return returnValue;
    }

}
