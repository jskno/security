package com.jskno.mykeycloak.eazybank.backend.app.utils;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SingletonCollector {

    public static <T, E extends RuntimeException> Collector<T, ?,T> toSingleton(E ex) {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                if (list.size() != 1) {
                    throw ex;
                }
                return list.get(0);
            }
        );
    }

}
