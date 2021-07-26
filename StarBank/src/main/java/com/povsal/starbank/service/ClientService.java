package com.povsal.starbank.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.utils.JSONConverter;

@Service
public class ClientService implements IClientService {
	
	@Autowired
	private JSONConverter converter;
	
	public List<Client> getAllClients() throws IOException {
		return converter.getAllClients();
	}

	public Client saveClient(Client person) throws IOException {
		if(clientIsAlreadyRegistered(person)) {
			return null;
		}
		converter.saveInJSONDb(person);
		return person;
	}

	public boolean clientIsAlreadyRegistered(Client person) throws IOException {
		List<Client> clients = converter.getAllClients();
		for(Client client : clients) {
			if(client.getIdentification() != null && person.getIdentification().equals(client.getIdentification())) {
				return true;
			}
		}
		return false;
	}
}
