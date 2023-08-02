package com.example.webshop.controller;

import com.example.webshop.dto.AddressInfo;
import com.example.webshop.dto.ProductCreateUpdateCommand;
import com.example.webshop.dto.ProductInfo;
import com.example.webshop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductContoller {
    private ProductService productService;
    @Autowired
    public ProductContoller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductInfo>> findAll() {
        log.info("Http request, GET /api/products");
        List<ProductInfo> productInfos = productService.listAddresses();
        return new ResponseEntity<>(productInfos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductInfo> save(@Valid @RequestBody ProductCreateUpdateCommand command) {
        log.info("Http request, POST /api/product, body: " + command.toString());
        ProductInfo beeInfo = productService.saveProdust(command);
        return new ResponseEntity<>(beeInfo, HttpStatus.CREATED);
    }

    //Todo findById


    @PutMapping("/{productId}")
    public ResponseEntity<ProductInfo> update(@PathVariable("productId") Long id,
                                          @Valid @RequestBody ProductCreateUpdateCommand command) {
        log.info("Http request, PUT /api/products/{productId} body: " + command.toString() +
                " with variable: " + id);
        ProductInfo updated = productService.update(id, command);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") Long id) {
        log.info("Http request, DELETE /api/products/{productId} with variable: " + id);
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
