package com.winestore.service.user.impl;

import com.winestore.domain.entity.user.Address;
import com.winestore.domain.entity.user.User;
import com.winestore.domain.repository.user.AddressRepository;
import com.winestore.service.user.AddressService;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final UserService userService;

    public Address getById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Address save(Address address) {
        User principal = userService.getPrincipal();

        Address userAddress = principal.getAddress();

        if (userAddress == null) {
            address = repository.save(address);
            principal.setAddress(address);
            userService.update(principal);
        } else {
            address.setId(userAddress.getId());
            address = repository.save(address);
        }

        return address;
    }
}
