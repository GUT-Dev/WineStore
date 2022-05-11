package com.winestore.api.dto.filters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class WineSearchFilter {
    private final Integer minPrice;
    private final Integer maxPrice;
    private final List<String> type;
    private final List<String> sweetness;
    private final String name;
    private final Boolean hasDiscount;
}
