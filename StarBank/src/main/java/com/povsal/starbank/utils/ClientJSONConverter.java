package com.povsal.starbank.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;
import com.povsal.starbank.utils.converter.JSONConverter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class ClientJSONConverter implements JSONConverter<String, Client> {

	private static final String DB_PATH_NATURAL = "src\\main\\resources\\static\\natural_clients.json";
	private static final String DB_PATH_COMPANY = "src\\main\\resources\\static\\company_clients.json";
	private static final String PARSING_ERROR = "Error parsing file";

	@Override
	public Map<String, Client> getAll() throws IOException {
		Map<String, Client> clients = new HashMap<>();
		clients.putAll(convertClientsFromJSON(true, null));
		clients.putAll(convertClientsFromJSON(false, null));
		return clients;
	}

	public void convert(Client client) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String filePath = "";
		if(client instanceof NaturalClient) {
			filePath = DB_PATH_NATURAL;
		} else if(client instanceof CompanyClient) {
			filePath = DB_PATH_COMPANY;
		}
		String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
		try(FileWriter file = new FileWriter(filePath)) {
			if(client instanceof NaturalClient) {
				Map<String, Client> clientsMap = convertClientsFromJSON(true, fileContent);
				if(clientsMap == null) {
					throw new IOException(PARSING_ERROR);
				}
				String identification = client.getIdentification();
				client.setIdentification(null);
				clientsMap.put(identification, client);
				gson.toJson(clientsMap, file);
			} else if(client instanceof CompanyClient) {
				Map<String, Client> clientsMap = convertClientsFromJSON(false, fileContent);
				if(clientsMap == null) {
					throw new IOException(PARSING_ERROR);
				}
				String nit = ((CompanyClient) client).getNit();
				((CompanyClient) client).setNit(null);
				clientsMap.put(nit, client);
				gson.toJson(clientsMap, file);
			}
		} catch (IOException e) {
			System.err.println(PARSING_ERROR);
		}
	}

	private Map<String, Client> convertClientsFromJSON(boolean natural, String fileContent) throws IOException {
		if(fileContent == null || fileContent.equalsIgnoreCase("")) {
			fileContent = new String(Files.readAllBytes(Paths.get(natural ? DB_PATH_NATURAL : DB_PATH_COMPANY)));
		}
		Gson gson = new Gson();
		Type typeOfMap;
		if(natural) {
			typeOfMap = new TypeToken<Map<String, NaturalClient>>() { }.getType();
		} else {
			typeOfMap = new TypeToken<Map<String, CompanyClient>>() { }.getType();
		}

		return gson.fromJson(fileContent, typeOfMap);
	}

	public String getAllClientsAsString() throws IOException {
		Map<String, Client> clients = new HashMap<>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		clients.putAll(convertClientsFromJSON(true, null));
		clients.putAll(convertClientsFromJSON(false, null));
		return gson.toJson(clients);
	}
}
