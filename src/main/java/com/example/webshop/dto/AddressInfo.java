package com.example.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {
    private Integer zipcode;
    private String city;
    private String street;
    private String housenumber;
    private List<CustomerInfo> customerList;
}
