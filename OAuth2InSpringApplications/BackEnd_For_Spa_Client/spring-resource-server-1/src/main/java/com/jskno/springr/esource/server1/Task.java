package com.jskno.springr.esource.server1;

import lombok.Builder;

@Builder
public record Task(
        String name,
        String frequency,
        String type
) {
}
