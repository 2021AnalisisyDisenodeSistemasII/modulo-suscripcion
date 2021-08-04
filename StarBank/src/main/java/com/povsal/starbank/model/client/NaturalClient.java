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
public class NaturalClient extends Client {
	@SerializedName(value = "client_address")
	private String address;
}
