package com.povsal.starbank.model.sucursal;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    private String sucursalId;
    private List<String> cashiers;
    private String city;
    private String account;

    @SerializedName("sucursal_address")
    private String address;
}
