package com.example.webshop.dto;

import com.example.webshop.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
