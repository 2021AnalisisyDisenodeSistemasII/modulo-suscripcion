package com.povsal.starbank.model.client;

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
	private String companyName;
	private String companyAddress;
	private String comercialSector;
}
