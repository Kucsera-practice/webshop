package com.example.webshop.controller;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<CustomerInfo> findCustomerByID(@PathVariable("id") Long id) {
        log.info("Http request, GET /api/customers/{Id} with variable: " + id);
        CustomerInfo customer = customerService.findById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerInfo>> findCustomerByName(@PathVariable("name") String name){
        log.info("Http request, GET /api/customers/{name} with variable: " + name);
        List<CustomerInfo> customerInfo = customerService.findByName(name);
        return new ResponseEntity<>(customerInfo, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<CustomerInfo>> findCustomerByCity(@PathVariable("city") String city){
        log.info("Http request, GET /api/customers/{city} with variable: " + city);
        List<CustomerInfo> customerInfo = customerService.findByCity(city);
        return new ResponseEntity<>(customerInfo, HttpStatus.OK);
    }

    @GetMapping("/zipcode/{zipcode}")
    public ResponseEntity<List<CustomerInfo>> findCustomerByAddress(@PathVariable("zipcode") Integer zipcode){
        log.info("Http request, GET /api/customers/{address} with variable: " + zipcode);
        List<CustomerInfo> customerInfo = customerService.findByZipcode(zipcode);
        return new ResponseEntity<>(customerInfo, HttpStatus.OK);
    }

    @PutMapping("{customerId}/addresses/{addressId}")
    public ResponseEntity<CustomerInfo> update(@PathVariable("customerId") Long customerId, @PathVariable("addressId") Long addressId){
        log.info("Http request, PUT /api/bee/{beeId} body:  With variable: " + customerId + " and " + addressId);
        CustomerInfo update = customerService.changeAddress(customerId, addressId);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

}
