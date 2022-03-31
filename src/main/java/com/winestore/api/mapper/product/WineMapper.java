package com.winestore.api.mapper.product;

import com.winestore.api.dto.product.WineDTO;
import com.winestore.api.dto.product.WineListDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.product.Wine;
import org.mapstruct.Mapper;

@Mapper
public interface WineMapper extends BaseMapper<Wine, WineDTO> {
    WineListDTO toListDTO(Wine entity);
}
