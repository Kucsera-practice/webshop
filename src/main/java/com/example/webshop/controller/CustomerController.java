package com.example.webshop.controller;

import com.example.webshop.domain.Customer;
import com.example.webshop.dto.CustomerCreateUpdateCommand;
import com.example.webshop.dto.CustomerInfo;
import com.example.webshop.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Slf4j
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody CustomerCreateUpdateCommand accountCommand) {
        customerService.saveCustomer(accountCommand);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerInfo>> findAll() {
        log.info("Http request, GET /api/customers");
        List<CustomerInfo> customerInfos = customerService.listCustomer();
        return new ResponseEntity<>(customerInfos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInfo> findCustomerByID(@PathVariable("id") Long id) {
        log.info("Http request, GET /api/customers/{Id} with variable: " + id);
        CustomerInfo customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

//    @GetMapping("/city")
//    public ResponseEntity<List<CustomerInfo>> findByType(@RequestParam(value = "city", required = false) Customer customer) {
//        log.info("Http request, GET /api/bee with parameter genre: " + customer);
//        List<CustomerInfo> customerInfos = customerService.findByCyíty(customer);
//        return new ResponseEntity<>(customerInfos, HttpStatus.OK);
//    }
}
