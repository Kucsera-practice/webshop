package com.example.webshop.service;

import com.example.webshop.domain.Product;
import com.example.webshop.dto.*;
import com.example.webshop.exceptionhandling.ProductNotFoundException;
import com.example.webshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public void delete(Long id) {
        Product toUpdate = findProductById(id);
        productRepository.delete(toUpdate);
    }

    private Product findProductById(Long id) {
        Optional<Product> hiveOptional = productRepository.findById(id);
        if (hiveOptional.isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return hiveOptional.get();
    }

    public ProductInfo saveProdust(ProductCreateUpdateCommand command) {
        Product toSave = modelMapper.map(command, Product.class);
        Product saved = productRepository.save(toSave);
        return modelMapper.map(saved, ProductInfo.class);
    }

    public ProductInfo update(Long id, ProductCreateUpdateCommand command) {
        Product toUpdate = findProductById(id);
        modelMapper.map(command, toUpdate);
        return modelMapper.map(toUpdate, ProductInfo.class);
    }

    public List<ProductInfo> listAddresses() {
        List<Product> products = productRepository.findAll();
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (Product product : products) {
            ProductInfo productInfo = modelMapper.map(product, ProductInfo.class);
            List<OrderProductInfoForProduct> orderProductInfos = product.getOrderProductList().stream()
                    .map(order -> modelMapper.map(order, OrderProductInfoForProduct.class))
                    .collect(Collectors.toList());
            productInfo.setOrderProductInfoList(orderProductInfos);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
