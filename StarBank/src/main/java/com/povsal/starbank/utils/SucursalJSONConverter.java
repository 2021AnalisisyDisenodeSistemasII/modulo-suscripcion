package com.povsal.starbank.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.povsal.starbank.model.sucursal.Sucursal;
import com.povsal.starbank.utils.converter.JSONConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class SucursalJSONConverter implements JSONConverter<String, Sucursal> {

    private static final String SUCURSALS_PATH = "src\\main\\resources\\static\\sucursals.json";

    @Override
    public Map<String, Sucursal> getAll() throws IOException {
        return new HashMap<>(convertFromJson());
    }

    private Map<String, Sucursal> convertFromJson() throws IOException {
        Gson gson = new Gson();
        Type typeOfMap = new TypeToken<Map<String, Sucursal>>() { }.getType();
        String fileContent = new String(Files.readAllBytes(Paths.get(SUCURSALS_PATH)));
        return gson.fromJson(fileContent, typeOfMap);
    }
}
