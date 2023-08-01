package com.example.webshop.service;

import com.example.webshop.domain.Address;
import com.example.webshop.domain.Customer;
import com.example.webshop.dto.AddressInfo;
import com.example.webshop.dto.CustomerCreateUpdateCommand;
import com.example.webshop.dto.CustomerInfo;
import com.example.webshop.exceptionhandling.CustomerNotFoundException;
import com.example.webshop.repository.CustomerRepository;
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
public class CustomerService {
    private CustomerRepository customerRepository;
    private AddressService addressService;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, AddressService addressService) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.addressService = addressService;
    }

    public CustomerInfo saveCustomer(CustomerCreateUpdateCommand command) {
        Customer toSave = modelMapper.map(command, Customer.class);
        Address addressForTheCustomer = addressService.findAddressById(command.getAddressId());
        toSave.setAddress(addressForTheCustomer);
        Customer saved = customerRepository.save(toSave);
        return modelMapper.map(saved, CustomerInfo.class);
    }

    public Customer findCustomerById(Long id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()){
            throw new CustomerNotFoundException(id);
        }
        return customerOptional.get();
    }

    public CustomerInfo findById(Long id) {
        return modelMapper.map(findCustomerById(id), CustomerInfo.class);
    }

    public CustomerInfo update(Long id, CustomerCreateUpdateCommand command) {
        Customer toUpdate = findCustomerById(id);
        modelMapper.map(command, toUpdate);
        return modelMapper.map(toUpdate, CustomerInfo.class);
    }

    public void delete(Long id) {
        Customer toDelete = findCustomerById(id);
        customerRepository.delete(toDelete);
    }

    public List<CustomerInfo> listCustomer() {
            return customerRepository.findAll().stream()
                    .map(customer -> modelMapper.map(customer, CustomerInfo.class))
                    .collect(Collectors.toList());
    }

    public List<CustomerInfo> findByCyíty(String city) {
        List<Customer> customers = customerRepository.findByCity(city);
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerInfo.class))
                .collect(Collectors.toList());
    }

}
