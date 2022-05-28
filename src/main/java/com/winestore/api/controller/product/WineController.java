package com.winestore.api.controller.product;

import com.winestore.api.dto.filters.WineSearchFilter;
import com.winestore.api.dto.product.BrandDTO;
import com.winestore.api.dto.product.LandDTO;
import com.winestore.api.dto.product.WineDTO;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.product.BrandMapper;
import com.winestore.api.mapper.product.LandMapper;
import com.winestore.api.mapper.product.WineMapper;
import com.winestore.domain.entity.product.Wine;
import com.winestore.enums.AvailableStatus;
import com.winestore.enums.Sweetness;
import com.winestore.enums.WineType;
import com.winestore.service.product.BrandService;
import com.winestore.service.product.LandService;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wine")
@RequiredArgsConstructor
public class WineController {

    private final WineService wineService;
    private final BrandService brandService;
    private final LandService landService;
    private final LandMapper landMapper;
    private final BrandMapper brandMapper;
    private final WineMapper wineMapper;

    @GetMapping("/{id}")
    public WineDTO getWine(@PathVariable(name = "id") Long id) {
        WineDTO dto = wineMapper.toDTO(wineService.getById(id));
        dto.setRating(wineService.countRating(dto.getId()));
        return dto;
    }

    @GetMapping
    public List<WineListDTO> getPage(WineSearchFilter filter, Pageable pageable) {
        return wineService.getPage(filter, pageable).stream()
            .map(wineMapper::toListDTO)
            .peek(i -> i.setRating(wineService.countRating(i.getId())))
            .toList();
    }

    @PostMapping
    public WineDTO create(@RequestBody WineDTO dto) {
        Wine wine = wineService.create(wineMapper.toEntity(dto));
        return wineMapper.toDTO(wine);
    }

    @GetMapping("/sweetness")
    public List<String> getSweetness() {
        return Arrays.stream(Sweetness.values())
            .map(Sweetness::name)
            .toList();
    }

    @GetMapping("/types")
    public List<String> getTypes() {
        return Arrays.stream(WineType.values())
            .map(WineType::name)
            .toList();
    }

    @GetMapping("/lands")
    public List<LandDTO> getLands() {
        return landService.getAll().stream()
            .map(landMapper::toDTO)
            .toList();
    }

    @GetMapping("/brands")
    public List<BrandDTO> getAll() {
        return brandService.getAll().stream()
            .map(brandMapper::toDTO)
            .toList();
    }
    
    @GetMapping("/statuses")
    public List<String> getStatuses() {
        return Arrays.stream(AvailableStatus.values())
            .map(AvailableStatus::name)
            .toList();
    }
}
