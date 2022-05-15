package com.winestore.api.dto.cart;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class UserCartDTO {
    private Set<CartItemDTO> items;
    private String totalPrice;
    private String totalPriceWithSale;
    private int totalSalePercent;
}
