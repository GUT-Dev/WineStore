package com.winestore.api.controller.product;

import com.winestore.api.dto.product.LandDTO;
import com.winestore.api.mapper.product.LandMapper;
import com.winestore.domain.entity.product.Land;
import com.winestore.service.product.LandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/manager")
    public LandDTO create(@RequestBody LandDTO dto) {
        Land land = service.create(mapper.toEntity(dto));
        return mapper.toDTO(land);
    }
}
