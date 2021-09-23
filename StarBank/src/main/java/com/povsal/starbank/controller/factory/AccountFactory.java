package com.povsal.starbank.controller.factory;

import com.povsal.starbank.model.account.Account;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
public class AccountFactory {

    public Account create(String clientId, String sucursalId, String type) {
        return Account.builder()
                .balance(0)
                .accountId(generateAccountId())
                .creationDate(new Date())
                .isActive(false)
                .sucursalId(sucursalId)
                .clientId(clientId)
                .type(type)
                .build();
    }

    private String generateAccountId() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 16;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
