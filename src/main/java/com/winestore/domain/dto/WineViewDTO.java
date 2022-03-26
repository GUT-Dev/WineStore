package com.winestore.domain.dto;

import com.winestore.domain.entity.product.Wine;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class WineViewDTO {
    private Wine wine;
    private Double rating;
}
