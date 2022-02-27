package com.friends.accountavroproducerconsumer.listeners;

import com.friends.accountservice.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.friends.accountavroproducerconsumer.exceptions.AccountServiceException;

@Service
public class AccountKafkaListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountKafkaListener.class);

    private final String ACCOUNT_TOPIC = "account";

    @Value("${account-enrichment.topic.name}")
    private String accountEnrichment;


    @Autowired
    private KafkaTemplate<String, Object> template;

    @KafkaListener(topics = ACCOUNT_TOPIC, groupId = "account_enrichment_group",
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeAccount(Account account) throws AccountServiceException {
        try {
            LOGGER.info("Account msg received : " + account);
            SendResult<String, Object> sendResult = template.send(accountEnrichment, String.valueOf(account.getId()),
                    account).get();
            LOGGER.info("Account msg sent to enrichment topic : " + sendResult.getProducerRecord().toString());
        } catch (Exception exception) {
            throw new AccountServiceException(exception.getMessage());
        }
    }
}

