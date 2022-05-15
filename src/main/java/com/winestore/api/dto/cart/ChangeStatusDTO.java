package com.winestore.api.dto.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ChangeStatusDTO {
    private final Long cartId;
    private final String status;
}
