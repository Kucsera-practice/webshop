package com.example.webshop.controller;

import com.example.webshop.dto.AddressCreateUpdateCommand;
import com.example.webshop.dto.AddressInfo;
import com.example.webshop.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@Slf4j
public class AddressController {
    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressInfo> createAddress(@Valid @RequestBody AddressCreateUpdateCommand command) {
        log.info("Http request, GET /api/addresses" + command.toString());
        AddressInfo addressInfo = addressService.saveAddress(command);
        return new ResponseEntity<>(addressInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AddressInfo>> findAll() {
        log.info("Http request, GET /api/addresses");
        List<AddressInfo> addressInfos = addressService.listAddresses();
        return new ResponseEntity<>(addressInfos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressInfo> findById(@PathVariable("id") Long id) {
        log.info("Http request, GET /api/addresses/{id} with variable" + id);
        AddressInfo addressInfo = addressService.findById(id);
        return new ResponseEntity<>(addressInfo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressInfo> update(@PathVariable("id") Long id, @Valid @RequestBody AddressCreateUpdateCommand command){
        log.info("Http request, PUT /api/bee/{beeId} body: " + command.toString() + " With variable: " + id);
        AddressInfo update = addressService.update(id, command);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        log.info("Http request, DELETE /api/addresses/{id} with variable: " + id);
       addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
