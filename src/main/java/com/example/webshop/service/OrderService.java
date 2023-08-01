package com.example.webshop.service;

import com.example.webshop.dto.OrderCreateUpdateCommand;
import com.example.webshop.dto.OrderInfo;
import com.example.webshop.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private ModelMapper modelMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderInfo> listOrders() {
        return orderRepository.findAll().stream()
                .map(order -> modelMapper.map(order, OrderInfo.class))
                .collect(Collectors.toList());
    }

    public OrderInfo saveOrder(OrderCreateUpdateCommand command) {

    }
}
