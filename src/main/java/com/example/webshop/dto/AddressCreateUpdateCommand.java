package com.example.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateUpdateCommand {
    private Integer zipcode;
    private String city;
    private String street;
    private String housenumber;
}
