package com.jskno.mykeycloak.eazybank.backend.app.exception;

public class DuplicatedCustomerException extends RuntimeException {

    public static final String DUPLICATED_CUSTOMER_ERROR_MSG = "Customer duplicated for email '%s'";

    public DuplicatedCustomerException(String customerEmail) {
        super(String.format(DUPLICATED_CUSTOMER_ERROR_MSG, customerEmail));
    }

}
