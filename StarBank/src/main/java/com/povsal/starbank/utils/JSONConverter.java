package com.povsal.starbank.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;
import com.povsal.starbank.utils.converter.IJSONConverter;

@Service
public class JSONConverter implements IJSONConverter {

	private static final String DB_PATH_NATURAL = "src\\main\\resources\\static\\natural_clients.json";
	private static final String DB_PATH_COMPANY = "src\\main\\resources\\static\\company_clients.json";
	
	public Map<String, Client> getAllClients() throws IOException {
		Map<String, Client> clients = new HashMap<>();
		clients.putAll(convertClientsFromJSON(true, null));
		clients.putAll(convertClientsFromJSON(false, null));
		return clients;
	}
	
	@Override
	public void saveInJSONDb(Client client) throws IOException {
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
					throw new IOException("Error parsing file");
				}
				clientsMap.put(client.getIdentification(), client);
				gson.toJson(clientsMap, file);
			} else if(client instanceof CompanyClient) {
				Map<String, Client> clientsMap = convertClientsFromJSON(false, fileContent);
				if(clientsMap == null) {
					throw new IOException("Error parsing file");
				}
				clientsMap.put(((CompanyClient) client).getNit(), client);
				gson.toJson(clientsMap, file);
			}
		} catch (IOException e) {
			System.err.println("Error parsing file");
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
}
