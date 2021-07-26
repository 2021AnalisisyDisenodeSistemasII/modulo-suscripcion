package com.povsal.starbank.service;

import java.io.IOException;
import java.util.List;

import com.povsal.starbank.model.client.Client;

public interface IClientService {
	public List<Client> getAllClients() throws IOException;
	public Client saveClient(Client client) throws IOException;
	public boolean clientIsAlreadyRegistered(Client client) throws IOException;
}
