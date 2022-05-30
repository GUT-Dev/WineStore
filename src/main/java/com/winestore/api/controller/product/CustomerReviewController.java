package com.winestore.api.controller.product;

import com.winestore.api.dto.product.AddReviewDTO;
import com.winestore.api.dto.product.CustomerReviewDTO;
import com.winestore.api.mapper.product.CustomerReviewMapper;
import com.winestore.domain.entity.product.CustomerReview;
import com.winestore.service.product.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final CustomerReviewService service;
    private final CustomerReviewMapper mapper;

    @PostMapping("/add")
    public CustomerReviewDTO createReview(@RequestBody AddReviewDTO review) {
        CustomerReview entity = service.create(mapper.toEntity(review));
        return mapper.toDTO(entity);
    }

    @GetMapping
    public List<CustomerReviewDTO> getReviewsForWine(@RequestParam Long wineId) {
        return service.getForWine(wineId).stream()
            .map(mapper::toDTO)
            .toList();
    }
}
