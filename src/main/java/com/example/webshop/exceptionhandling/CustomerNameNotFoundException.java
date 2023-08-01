package com.example.webshop.exceptionhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerNameNotFoundException extends RuntimeException {
    private final String name;
}
