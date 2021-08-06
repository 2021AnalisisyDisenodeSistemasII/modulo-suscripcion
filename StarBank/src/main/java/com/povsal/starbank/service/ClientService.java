package com.povsal.starbank.service;

import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;
import com.povsal.starbank.utils.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ClientService implements IClientService {
	
	@Autowired
	private JSONConverter converter;
	
	public Map<String, Client> getAllClients() throws IOException {
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
		Map<String, Client> clients = converter.getAllClients();
		if(clients == null) {
			return false;
		}
		for(String document : clients.keySet()) {
			if(person instanceof NaturalClient) {
				if(document.equalsIgnoreCase(person.getIdentification())) return true;
			} else if (person instanceof CompanyClient) {
				if(document.equalsIgnoreCase(((CompanyClient) person).getNit())) return true;
			}
		}
		return false;
	}

    public String getAllClientsAsString() throws IOException {
		return converter.getAllClientsAsString();
    }
}
