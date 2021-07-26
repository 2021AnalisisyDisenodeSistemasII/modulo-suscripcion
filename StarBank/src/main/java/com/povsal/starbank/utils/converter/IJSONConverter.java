package com.povsal.starbank.utils.converter;

import java.io.IOException;
import java.util.List;

import com.povsal.starbank.model.client.Client;
import com.povsal.starbank.model.client.CompanyClient;
import com.povsal.starbank.model.client.NaturalClient;

public interface IJSONConverter extends Converter {
	public List<NaturalClient> convertNaturalClientFromJSON() throws IOException;
	public List<CompanyClient> convertCompanyClientFromJSON() throws IOException;
	public void saveInJSONDb(Client client) throws IOException;
}
