package com.povsal.starbank.service;

import com.povsal.starbank.model.client.Client;

import java.io.IOException;
import java.util.Map;

public interface IClientService {
	public Map<String, Client> getAllClients() throws IOException;
	public Client saveClient(Client client) throws IOException;
	public boolean clientIsAlreadyRegistered(Client client) throws IOException;
}
