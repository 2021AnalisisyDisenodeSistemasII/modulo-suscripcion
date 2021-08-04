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
public class Client {
	@SerializedName(value = "phone")
	protected String contactNumber;

	@SerializedName(value = "client_name")
	protected String contactName;

	@SerializedName(value = "client_occupation")
	protected String contactOccupation;

	@SerializedName(value = "client_id")
	protected String identification;
}
