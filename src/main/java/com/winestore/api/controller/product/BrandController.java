package com.winestore.api.controller.product;

import com.winestore.api.dto.product.BrandDTO;
import com.winestore.api.mapper.product.BrandMapper;
import com.winestore.domain.entity.product.Brand;
import com.winestore.service.product.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService service;
    private final BrandMapper mapper;

    @GetMapping
    public List<BrandDTO> getAll() {
        return service.getAll().stream()
            .map(mapper::toDTO)
            .toList();
    }

    @PostMapping("/manager")
    public BrandDTO create(@RequestBody BrandDTO dto) {
        Brand brand = service.create(mapper.toEntity(dto));
        return mapper.toDTO(brand);
    }
}
