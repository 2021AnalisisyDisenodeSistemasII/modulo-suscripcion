package com.povsal.starbank.service.definition;

import com.povsal.starbank.model.account.Account;

import java.io.IOException;

public interface IAccountService {
    boolean accountBelongsToClient(String clientId, String accountId) throws IOException;
    boolean accountAlreadyExists(String accountId) throws IOException;
    void createAccount(Account temporalAccount) throws IOException;
}
