package com.povsal.starbank.controller;

import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;
import com.povsal.starbank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "'http://127.0.0.1:5500")
@Controller
@RequestMapping(path = "/persons")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/all")
	public ResponseEntity<Map<String, Client>> getAllPersons() {
		Map<String, Client> clients = null;
		try {
			clients = clientService.getAllClients();
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(clients, HttpStatus.OK);
	}
	
	@PostMapping("/natural")
	public ResponseEntity<Client> saveNaturalClient(NaturalClient body) {
		Client client;
		try {
			client = clientService.saveClient(body);
			if(client == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
	
	@PostMapping("/company")
	public ResponseEntity<Client> saveCompanyClient(CompanyClient body) {
		Client client;
		try {
			client = clientService.saveClient(body);
			if(client == null) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(client, HttpStatus.CREATED);
	}
}
