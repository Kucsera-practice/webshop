package com.example.webshop.service;

import com.example.webshop.domain.Address;
import com.example.webshop.domain.Order;
import com.example.webshop.domain.OrderProduct;
import com.example.webshop.dto.*;
import com.example.webshop.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<Order> orders = orderRepository.findAll();
        List<OrderInfo> orderInfoList = new ArrayList<>();
        for (Order order : orders) {
            OrderInfo orderInfo = modelMapper.map(order, OrderInfo.class);
            List<OrderProductInfoForOrder> orderProductInfoForOrders = order.getOrderProductList().stream()
                    .map(orderProduct ->  modelMapper.map(orderProduct,OrderProductInfoForOrder.class))
                    .collect(Collectors.toList());
            orderInfo.setOrderProductList(orderProductInfoForOrders);
            orderInfoList.add(orderInfo);
        }
        return orderInfoList;
    }

    public OrderInfo saveOrder(OrderCreateUpdateCommand command) {
        Order toSave = modelMapper.map(command, Order.class);
        Order saved = orderRepository.save(toSave);
        return modelMapper.map(saved, OrderInfo.class);
    }
}
