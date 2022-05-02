package com.winestore.api.dto.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
public class UserCartDTO {
    private Set<CartItemDTO> items;
    private String totalPrice;
    private String totalPriceWithSale;
    private int totalSalePercent;
}
