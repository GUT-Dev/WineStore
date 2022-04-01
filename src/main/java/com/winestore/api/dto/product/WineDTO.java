package com.winestore.api.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WineDTO extends WineListDTO {
    private String descriptions;
    private String type;
    private String sweets;
    private double strength;
    private int sugarAmount;
    private String ean;
    private BrandDTO brand;
    private LandDTO land;
}
