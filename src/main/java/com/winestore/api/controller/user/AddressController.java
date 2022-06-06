package com.winestore.api.controller.user;

import com.winestore.api.dto.user.AddressDTO;
import com.winestore.api.mapper.user.AddressMapper;
import com.winestore.domain.entity.user.Address;
import com.winestore.service.user.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;
    private final AddressMapper mapper;

    @PostMapping
    public AddressDTO save(@RequestBody AddressDTO addressDTO) {
        Address entity = service.save(mapper.toEntity(addressDTO));
        return mapper.toDTO(entity);
    }
}
