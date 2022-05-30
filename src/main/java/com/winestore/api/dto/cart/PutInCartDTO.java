package com.winestore.api.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutInCartDTO {
    private Long wineId;
    private int amount;
}
