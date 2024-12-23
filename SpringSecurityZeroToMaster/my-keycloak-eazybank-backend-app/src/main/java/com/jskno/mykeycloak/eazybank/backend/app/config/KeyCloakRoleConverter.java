package com.jskno.mykeycloak.eazybank.backend.app.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.ObjectUtils;

public class KeyCloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realAccess = (Map<String, Object>) source.getClaims().get("realm_access");

        if (ObjectUtils.isEmpty(realAccess)) {
            return new ArrayList<>();
        }

        Collection<GrantedAuthority> authorities = ((List<String>) realAccess.get("roles"))
            .stream()
            .map(roleName -> "ROLE_" + roleName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        return authorities;
    }

}
