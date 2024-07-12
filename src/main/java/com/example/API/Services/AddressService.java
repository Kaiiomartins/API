package com.example.API.Services;

import com.example.API.Domain.Address.Address;
import com.example.API.Domain.Eventos.Event;
import com.example.API.Domain.Eventos.EventRequestDTO;
import com.example.API.Repositorios.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(EventRequestDTO data, Event event) {
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(event);

        return addressRepository.save(address);
    }
}