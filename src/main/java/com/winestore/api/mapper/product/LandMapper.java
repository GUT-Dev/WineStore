package com.winestore.api.mapper.product;

import com.winestore.api.dto.product.LandDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.product.Land;
import org.mapstruct.Mapper;

@Mapper
public interface LandMapper extends BaseMapper<Land, LandDTO> {
}
