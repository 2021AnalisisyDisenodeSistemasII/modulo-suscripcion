package com.povsal.starbank.controller;

import com.povsal.starbank.controller.factory.AccountFactory;
import com.povsal.starbank.model.account.Account;
import com.povsal.starbank.service.implementation.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "'http://127.0.0.1:5500")
@Controller
@RequestMapping(path = "/persons")
public class AccountController {

    @Autowired
    private AccountService service;

    @Autowired
    private AccountFactory factory;

    @GetMapping("/accounts/check")
    public ResponseEntity<Boolean> accountsBelongsToClient(@RequestParam(name = "client_id") String clientId, @RequestParam(name = "account_id") String accountId) {
        try {
            return new ResponseEntity<>(this.service.accountBelongsToClient(clientId, accountId), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/accounts/create")
    public ResponseEntity<Boolean> createAccount(String clientId, String sucursalId, String type) {
        Account account = this.factory.create(clientId, sucursalId, type);
        try {
            this.service.createAccount(account);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
