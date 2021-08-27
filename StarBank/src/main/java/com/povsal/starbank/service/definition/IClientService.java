package com.povsal.starbank.service.definition;

import com.povsal.starbank.model.client.Client;

import java.io.IOException;
import java.util.Map;

public interface IClientService {
	Map<String, Client> getAllClients() throws IOException;
	Client saveClient(Client client) throws IOException;
	boolean clientIsAlreadyRegistered(Client client) throws IOException;
}
