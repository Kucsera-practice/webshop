package com.example.webshop.service;

import com.example.webshop.domain.Address;
import com.example.webshop.dto.AddressCreateUpdateCommand;
import com.example.webshop.dto.AddressInfo;
import com.example.webshop.dto.CustomerInfo;
import com.example.webshop.exceptionhandling.AddressNotFoundException;
import com.example.webshop.repository.AddressRepository;
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
public class AddressService {
    private AddressRepository addressRepository;
    private CustomerService customerService;
    private ModelMapper modelMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    public AddressInfo saveAddress(AddressCreateUpdateCommand command){
        Address toSave = modelMapper.map(command, Address.class);
        Address saved = addressRepository.save(toSave);
        return modelMapper.map(saved,AddressInfo.class);
    }

    public List<AddressInfo> listAddresses(){
        List<Address> addresses = addressRepository.findAll();                                                  //megkeresi az öszes addrest
        List<AddressInfo> addressInfoList = new ArrayList<>();                                                  //létrehoz egy új address info listát.
        for (Address address : addresses) {                                                                      //végigmegy az összes addressen
            AddressInfo addressInfo = modelMapper.map(address, AddressInfo.class);                              //létrehoz egy address infót és beletölti az adress adatait mindegyiknek.
            List<CustomerInfo> customerInfos = address.getCustomerList().stream()                               //létrehoz a forcikluson belül egy customerinfólistát ami az eddreszben található customerlistet kapja meg értékül
                    .map(customer -> modelMapper.map(customer,CustomerInfo.class))
                    .collect(Collectors.toList());
            addressInfo.setCustomerInfoList(customerInfos);
            addressInfoList.add(addressInfo);
        }
        return addressInfoList;
    }

    public Address findAddressById(Long id){
        Optional<Address> addressOptional = addressRepository.findById(id);
        if (addressOptional.isEmpty()){
            throw new AddressNotFoundException(id);
        }
        return addressOptional.get();
    }

    public AddressInfo findById(Long id) {
        return modelMapper.map(findAddressById(id),AddressInfo.class);
    }

    public void delete(Long id) {
        Address toDelete = findAddressById(id);
        addressRepository.delete(toDelete);
    }

    public AddressInfo update(Long id, AddressCreateUpdateCommand command) {
        Address toUpdate = findAddressById(id);
        modelMapper.map(command,toUpdate);
        return modelMapper.map(toUpdate, AddressInfo.class);
    }
}
