package com.povsal.starbank.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;
import com.povsal.starbank.utils.converter.IJSONConverter;

@Service
public class JSONConverter implements IJSONConverter {

	private final static String DB_PATH_NATURAL = "src\\main\\resources\\static\\natural_clients.json";
	private final static String DB_PATH_COMPANY = "src\\main\\resources\\static\\company_clients.json";
	private static FileWriter file;
	
	public List<Client> getAllClients() throws IOException {
		List<Client> clients = new ArrayList<Client>();
		clients.addAll(convertNaturalClientFromJSON());
		clients.addAll(convertCompanyClientFromJSON());
		return clients;
	}
	
	@Override
	public void saveInJSONDb(Client client) throws IOException {
		Gson gson = new Gson();
		try {
			if(client instanceof NaturalClient) {
				List<NaturalClient> clients = convertNaturalClientFromJSON();
				clients.add((NaturalClient) client);
				NaturalClient[] p = clients.toArray(new NaturalClient[clients.size()]);
				file = new FileWriter(DB_PATH_NATURAL);
				gson.toJson(p, file);
			} else if(client instanceof CompanyClient) {
				List<CompanyClient> clients = convertCompanyClientFromJSON();
				clients.add((CompanyClient) client);
				CompanyClient[] p = clients.toArray(new CompanyClient[clients.size()]);
				file = new FileWriter(DB_PATH_COMPANY);
				gson.toJson(p, file);
			}
		} finally {
			file.flush();
            file.close();
		}
	}

	@Override
	public List<NaturalClient> convertNaturalClientFromJSON() throws IOException {
		String file = new String(Files.readAllBytes(Paths.get(DB_PATH_NATURAL)));
        JsonArray persons = null;
        JsonElement parsedElement = JsonParser.parseString(file);
        if(parsedElement == null) {
        	return new ArrayList<NaturalClient>();
        }
        persons = (JsonArray) parsedElement.getAsJsonArray();
        Gson gson = new Gson();
		NaturalClient[] p = gson.fromJson(persons, NaturalClient[].class);
		List<NaturalClient> p2 = new ArrayList<NaturalClient>(Arrays.asList(p));
		return p2;
	}

	@Override
	public List<CompanyClient> convertCompanyClientFromJSON() throws IOException {
		String file = new String(Files.readAllBytes(Paths.get(DB_PATH_COMPANY)));
        JsonArray persons = null;
        JsonElement parsedElement = JsonParser.parseString(file);
        if(parsedElement == null) {
        	return new ArrayList<CompanyClient>();
        }
        persons = (JsonArray) parsedElement.getAsJsonArray();
        Gson gson = new Gson();
        CompanyClient[] p = gson.fromJson(persons, CompanyClient[].class);
		List<CompanyClient> p2 = new ArrayList<CompanyClient>(Arrays.asList(p));
		return p2;
	}
}
