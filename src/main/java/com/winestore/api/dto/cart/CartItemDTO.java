package com.winestore.api.dto.cart;

import com.winestore.api.dto.product.WineListDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private WineListDTO wine;
    private int amount;
    private boolean available;
}
