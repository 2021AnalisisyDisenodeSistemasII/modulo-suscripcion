package com.povsal.starbank.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Client {
	protected String contactNumber;
	protected String contactName;
	protected String contactOcupation;
	protected String identificationType;
	protected String identification;
}
