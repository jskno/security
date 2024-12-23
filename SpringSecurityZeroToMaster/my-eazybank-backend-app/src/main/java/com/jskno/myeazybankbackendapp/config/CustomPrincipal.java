package com.jskno.myeazybankbackendapp.config;

import java.security.Principal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
 @RequiredArgsConstructor
public class CustomPrincipal implements Principal {
    private final String name;
    private final Long customerId;

    @Override
    public String toString() {
        return getName();
    }
}
