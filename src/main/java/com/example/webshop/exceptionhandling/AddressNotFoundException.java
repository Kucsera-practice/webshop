package com.example.webshop.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressNotFoundException extends RuntimeException {
    private final Long addressId;
}
