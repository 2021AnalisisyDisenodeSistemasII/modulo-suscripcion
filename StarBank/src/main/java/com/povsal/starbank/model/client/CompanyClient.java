package com.povsal.starbank.model.client;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyClient extends Client {

	private String nit;

	@SerializedName(value = "company_name")
	private String companyName;

	@SerializedName(value = "client_address")
	private String companyAddress;

	@SerializedName(value = "cluster")
	private String commercialSector;
}
