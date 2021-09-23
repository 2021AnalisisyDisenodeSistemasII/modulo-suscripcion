package com.povsal.starbank.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.povsal.starbank.model.account.Account;
import com.povsal.starbank.utils.converter.JSONConverter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountJSONConverter implements JSONConverter<String, Account> {

    private static final String DB_PATH_SAVINGS = "src\\main\\resources\\static\\saving_accounts.json";
    private static final String DB_PATH_CURRENT = "src\\main\\resources\\static\\current_accounts.json";
    private static final String PARSING_ERROR = "Error parsing file";

    @Override
    public Map<String, Account> getAll() throws IOException {
        Map<String, Account> accounts = new HashMap<>();
        accounts.putAll(convertFromJson(true, null));
        accounts.putAll(convertFromJson(false, null));
        return accounts;
    }

    public void saveAccount(Account account) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("dd-MM-yyyy").create();
        String accountType = account.getType();
        String filePath = accountType.equals("SAVINGS") ? DB_PATH_SAVINGS : DB_PATH_CURRENT;
        String fileContent = new String(Files.readAllBytes(Paths.get(accountType.equals("SAVINGS") ? DB_PATH_SAVINGS : DB_PATH_CURRENT)));
        try(FileWriter file = new FileWriter(filePath)) {
            String accountId = account.getAccountId();
            account.setAccountId(null);
            account.setType(null);
            Map<String, Account> accounts = null;
            if(accountType.equals("SAVINGS")) {
                accounts = convertFromJson(true, fileContent);
            } else if(accountType.equals("CURRENT")) {
                accounts = convertFromJson(false, fileContent);
            } else {
                throw new IOException("Wrong account type");
            }

            if(accounts == null) {
                throw new IOException(PARSING_ERROR);
            }
            accounts.put(accountId, account);
            gson.toJson(accounts, file);
        } catch (IOException e) {
            System.err.println(PARSING_ERROR);
        }
    }

    private Map<String, Account> convertFromJson(boolean savings, String fileContent) throws IOException {
        if(fileContent == null || fileContent.equalsIgnoreCase("")) {
            fileContent = new String(Files.readAllBytes(Paths.get(savings ? DB_PATH_SAVINGS : DB_PATH_CURRENT)));
        }
        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();
        Type typeOfMap = new TypeToken<Map<String, Account>>() { }.getType();
        return gson.fromJson(fileContent, typeOfMap);
    }
}
