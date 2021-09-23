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

    @Autowired
    private ClientService clientService;

    @Override
    public boolean accountBelongsToClient(String clientId, String accountId) throws IOException {
        Map<String, Account> accounts = converter.getAll();
        if(!accounts.containsKey(accountId)) return false;
        Account account = accounts.get(accountId);
        return account.getClientId().equalsIgnoreCase(clientId);
    }

    @Override
    public boolean accountAlreadyExists(String accountId) throws IOException {
        Map<String, Account> accounts = converter.getAll();
        return accounts.containsKey(accountId);
    }

    @Override
    public void createAccount(Account account) throws IOException {
        if (!clientService.getAllClients().containsKey(account.getClientId())) {
            throw new IOException("Cliente no existe");
        }
        if (converter.getAll().containsKey(account.getAccountId())) {
            throw new IOException("Cuenta ya existe");
        }
        converter.saveAccount(account);
    }
}
