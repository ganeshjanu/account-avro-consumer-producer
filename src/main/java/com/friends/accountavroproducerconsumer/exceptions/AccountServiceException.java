package com.friends.accountavroproducerconsumer.exceptions;

public class AccountServiceException extends Exception {

    public AccountServiceException(String errorMsg) {
        super(errorMsg);
    }
}
