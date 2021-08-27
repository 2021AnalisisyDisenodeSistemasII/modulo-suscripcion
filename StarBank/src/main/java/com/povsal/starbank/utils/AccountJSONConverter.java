package com.povsal.starbank.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.povsal.starbank.model.account.Account;
import com.povsal.starbank.utils.converter.JSONConverter;
import org.springframework.stereotype.Component;

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

    @Override
    public Map<String, Account> getAll() throws IOException {
        Map<String, Account> accounts = new HashMap<>();
        accounts.putAll(convertFromJson(true));
        accounts.putAll(convertFromJson(false));
        return accounts;
    }

    private Map<String, Account> convertFromJson(boolean savings) throws IOException {
        Gson gson = new Gson();
        Type typeOfMap = new TypeToken<Map<String, Account>>() { }.getType();
        String fileContent = new String(Files.readAllBytes(Paths.get(savings ? DB_PATH_SAVINGS : DB_PATH_CURRENT)));
        return gson.fromJson(fileContent, typeOfMap);
    }
}
