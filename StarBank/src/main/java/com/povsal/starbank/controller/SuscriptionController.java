package com.povsal.starbank.controller;

import com.povsal.starbank.service.implementation.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@CrossOrigin(origins = "'http://127.0.0.1:5500")
@Controller
@RequestMapping(path = "/persons")
public class SuscriptionController {

    @Autowired
    private AccountService service;

    @GetMapping("/accounts/check")
    public ResponseEntity<Boolean> accountsBelongsToClient(@RequestParam(name = "client_id") String clientId, @RequestParam(name = "account_id") String accountId) {
        try {
            return new ResponseEntity<>(this.service.accountBelongsToClient(clientId, accountId), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
