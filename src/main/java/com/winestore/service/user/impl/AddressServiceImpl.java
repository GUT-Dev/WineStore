package com.winestore.service.user.impl;

import com.winestore.domain.entity.user.Address;
import com.winestore.domain.repository.user.AddressRepository;
import com.winestore.service.user.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;

    public Address getById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Address create(Address address) {
        return repository.save(address);
    }

    @Override
    public Address update(Address address) {
        if (repository.existsById(address.getId())) {
            return repository.save(address);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
