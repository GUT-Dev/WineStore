package com.winestore.api.mapper.cart;

import com.winestore.api.dto.cart.CartItemDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.cart.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem, CartItemDTO> {

    @Override
    @Mapping(target = "wine.price", qualifiedByName = "convertPrice")
    @Mapping(target = "wine.priceWithSale", qualifiedByName = "convertPrice")
    CartItemDTO toDTO(CartItem entity);

    @Named("convertPrice")
    static String convertPrice(BigDecimal price) {
        return price
            .movePointLeft(2)
            .toPlainString();
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(String price) {
        return new BigDecimal(price)
            .setScale(2, RoundingMode.HALF_EVEN)
            .movePointRight(2);
    }
}
