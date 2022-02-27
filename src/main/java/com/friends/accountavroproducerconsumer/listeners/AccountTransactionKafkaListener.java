package com.friends.accountavroproducerconsumer.listeners;

import com.friends.accountservice.AccountTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AccountTransactionKafkaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTransactionKafkaListener.class);

    private final String ACCOUNT_TRANSACTION_TOPIC = "account-transaction";

    @KafkaListener(topics = ACCOUNT_TRANSACTION_TOPIC)
    public void consumeAccount(AccountTransaction accountTransaction) {
        LOGGER.info("Account Transaction Obj : " + accountTransaction);
    }
}
