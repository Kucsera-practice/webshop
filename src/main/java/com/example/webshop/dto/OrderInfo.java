package com.example.webshop.dto;

import com.example.webshop.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private Date orderDate;
    private List<OrderProduct> orderProductList;
}
