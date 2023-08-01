package com.example.webshop.controller;

import com.example.webshop.dto.CustomerCreateUpdateCommand;
import com.example.webshop.dto.CustomerInfo;
import com.example.webshop.dto.OrderCreateUpdateCommand;
import com.example.webshop.dto.OrderInfo;
import com.example.webshop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Slf4j
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody OrderCreateUpdateCommand command) {
        log.info("/api/orders " + command);
        orderService.saveOrder(command);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderInfo>> findAll(){
        log.info("Http request, GET /api/customers");
        log.info("Alma");
        List<OrderInfo> orderInfos = orderService.listOrders();
        return new ResponseEntity<>(orderInfos, HttpStatus.OK);
    }
}
