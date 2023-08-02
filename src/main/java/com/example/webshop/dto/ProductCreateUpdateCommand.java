package com.example.webshop.dto;

import com.example.webshop.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateUpdateCommand {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(value = 1, message = "Must not be 0")
    private Double price;

    private ProductType productType;

    private Long orderId;
}
