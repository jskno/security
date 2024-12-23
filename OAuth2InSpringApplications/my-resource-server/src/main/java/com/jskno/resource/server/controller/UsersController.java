package com.jskno.resource.server.controller;

import com.jskno.resource.server.domain.UserRest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final Environment env;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + env.getProperty("local.server.port");
    }

    @Secured("ROLE_developer")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        return "Delete user with id " + id;
    }

    @PreAuthorize("hasRole('developer')")
//    @PreAuthorize("hasAuthority('ROLE_developer')")
    @PutMapping("modify/{id}")
    public String modifyUser(@PathVariable String id) {
        return "Modifying user with id " + id;
    }

    @PreAuthorize("hasRole('developer') and #id == #jwt.subject")
    @PutMapping("modify2/{id}")
    public String modify2User(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Modifying user with id " + id + " and JWT subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.id == #jwt.subject")
    @GetMapping("/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return UserRest.builder()
            .id("4df170e6-aeae-4d98-b08d-23d2da93becc")
            .firstName("Jose")
            .lastName("Cano").build();
    }

}
