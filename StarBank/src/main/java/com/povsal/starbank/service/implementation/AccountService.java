package com.povsal.starbank.service.implementation;

import com.povsal.starbank.model.account.Account;
import com.povsal.starbank.service.definition.IAccountService;
import com.povsal.starbank.utils.AccountJSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountJSONConverter converter;

    @Override
    public boolean accountBelongsToClient(String clientId, String accountId) throws IOException {
        Map<String, Account> accounts = converter.getAll();
        if(!accounts.containsKey(accountId)) return false;
        Account account = accounts.get(accountId);
        return account.getClientId().equalsIgnoreCase(clientId);
    }
}
