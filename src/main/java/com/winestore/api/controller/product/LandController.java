package com.winestore.api.controller.product;

import com.winestore.api.dto.product.LandDTO;
import com.winestore.api.mapper.product.LandMapper;
import com.winestore.service.product.LandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/land")
@RequiredArgsConstructor
public class LandController {

    private final LandService service;
    private final LandMapper mapper;

    @GetMapping
    public List<LandDTO> getAll() {
        return service.getAll().stream()
            .map(mapper::toDTO)
            .toList();
    }
}
