package com.example.webshop.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
public class ProductNotFoundException extends RuntimeException{
    private final Long id;
}
