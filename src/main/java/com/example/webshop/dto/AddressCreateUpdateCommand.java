package com.example.webshop.dto;

import com.example.webshop.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCreateUpdateCommand {
    private Integer zipcode;
    private String city;
    private String street;
    private String housenumber;
}
