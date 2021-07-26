package com.povsal.starbank.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;
import com.povsal.starbank.service.ClientService;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping(path = "/persons")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/")
	public String index() {
		return "index.html";
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Client>> getAllPersons() {
		List<Client> clients = null;
		try {
			clients = clientService.getAllClients();
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
	
	@PostMapping("/natural")
	public ResponseEntity<Client> saveNaturalClient(NaturalClient client) {
		Client _client = null;
		try {
			_client = clientService.saveClient(client);
			if(_client == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(_client, HttpStatus.CREATED);
	}
	
	@PostMapping("/company")
	public ResponseEntity<Client> saveCompanyClient(CompanyClient client) {
		Client _client = null;
		try {
			_client = clientService.saveClient(client);
			if(_client == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(_client, HttpStatus.CREATED);
	}
}
