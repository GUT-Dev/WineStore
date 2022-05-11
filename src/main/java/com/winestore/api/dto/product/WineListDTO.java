package com.winestore.api.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WineListDTO {
    private Long id;
    private String name;
    private String img;
    private String price;
    private String priceWithSale;
    private int discount;
    private boolean available;
}
