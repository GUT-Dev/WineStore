package com.winestore.api.mapper.product;

import com.winestore.api.dto.product.WineDTO;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.product.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper
public interface WineMapper extends BaseMapper<Wine, WineDTO> {
    @Mapping(target = "price", qualifiedByName = "priceToString")
    WineListDTO toListDTO(Wine entity);

    @Override
    @Mapping(target = "price", qualifiedByName = "priceToBigDecimal")
    Wine toEntity(WineDTO dto);

    @Override
    @Mapping(target = "price", qualifiedByName = "priceToString")
    WineDTO toDTO(Wine entity);

    @Named("priceToString")
    static String priceToString(BigDecimal price) {
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
