package com.example.webshop.dto;

import com.example.webshop.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {

    private String name;
    private double price;
    private ProductType productType;
    private List<OrderProductInfoForProduct> orderProductInfoList;
}
