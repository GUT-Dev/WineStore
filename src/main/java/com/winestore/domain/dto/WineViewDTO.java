package com.winestore.domain.dto;

import com.winestore.api.dto.product.WineListDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class WineViewDTO {
    private WineListDTO wine;
    private Double rating;
}
