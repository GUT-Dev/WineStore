package com.winestore.api.dto.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddReviewDTO {
    private final Long wineId;
    private final String message;
    private final int rating;
}
