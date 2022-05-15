package com.winestore.api.controller.product;

import com.winestore.api.dto.filters.WineSearchFilter;
import com.winestore.api.dto.product.WineDTO;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.product.WineMapper;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wine")
@RequiredArgsConstructor
public class WineController {

    private final WineService service;
    private final WineMapper mapper;

    @GetMapping("/{id}")
    public WineDTO getWine(@PathVariable(name = "id") Long id) {
        WineDTO dto = mapper.toDTO(service.getById(id));
        dto.setRating(service.countRating(dto.getId()));
        return dto;
    }

    @GetMapping
    public List<WineListDTO> getPage(WineSearchFilter filter, Pageable pageable) {
        return service.getPage(filter, pageable).stream()
            .map(mapper::toListDTO)
            .peek(i -> i.setRating(service.countRating(i.getId())))
            .toList();
    }
}
