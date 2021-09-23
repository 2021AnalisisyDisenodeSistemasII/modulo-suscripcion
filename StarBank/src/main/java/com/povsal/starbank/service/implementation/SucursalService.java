package com.povsal.starbank.service.implementation;

import com.povsal.starbank.service.definition.ISucursalService;
import com.povsal.starbank.utils.SucursalJSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SucursalService implements ISucursalService {

    @Autowired
    private SucursalJSONConverter converter;

    @Override
    public boolean sucursalExists(String sucursalId) throws IOException {
        return converter.getAll().containsKey(sucursalId);
    }
}
