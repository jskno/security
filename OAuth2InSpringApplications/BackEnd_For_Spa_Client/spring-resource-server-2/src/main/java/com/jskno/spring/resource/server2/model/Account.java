package com.jskno.spring.resource.server2.model;

import lombok.Builder;

@Builder
public record Account(
        String name,
        String iban
) {
}
