package com.winestore.api.controller.product;

import com.winestore.api.dto.filters.WineSearchFilter;
import com.winestore.api.dto.product.WineDTO;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.product.WineMapper;
import com.winestore.domain.entity.product.Wine;
import com.winestore.enums.AvailableStatus;
import com.winestore.enums.Sweetness;
import com.winestore.enums.WineType;
import com.winestore.service.product.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
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
    public Page<WineListDTO> getPage(WineSearchFilter filter, Pageable pageable) {
        return service.getPage(filter, pageable)
            .map(mapper::toListDTO);
    }

    @PostMapping
    public WineDTO create(@RequestBody WineDTO dto) {
        Wine wine = service.create(mapper.toEntity(dto));
        return mapper.toDTO(wine);
    }

    @PostMapping("/upload-img")
    public String uploadImgForWine(@RequestParam Long wineId,
                                   @RequestBody MultipartFile file) throws IOException {
        return service.uploadImgForWine(wineId, file);
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

    @GetMapping("/max-price")
    public String getMaxPrice() {
        return service.getMaxPrice()
            .movePointLeft(2)
            .setScale(0)
            .toString();
    }

    @GetMapping("/min-price")
    public String getMinPrice() {
        return service.getMinPrice()
            .movePointLeft(2)
            .setScale(0)
            .toString();
    }

    @GetMapping("/statuses")
    public List<String> getStatuses() {
        return Arrays.stream(AvailableStatus.values())
            .map(AvailableStatus::name)
            .toList();
    }
}
