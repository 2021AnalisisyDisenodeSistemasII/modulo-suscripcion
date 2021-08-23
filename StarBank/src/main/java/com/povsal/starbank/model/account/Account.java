package com.povsal.starbank.model.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @SerializedName(value = "account_id")
    private String accountId;

    @SerializedName(value = "creation_date")
    private LocalDate creationDate;

    @SerializedName(value = "client_id")
    private String clientId;

    @SerializedName(value = "sucursal_id")
    private String sucursalId;

    private boolean isActive;

    private double balance;

    private List<String> transactions;
}
