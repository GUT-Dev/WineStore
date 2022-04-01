package com.winestore.api.mapper.product;

import com.winestore.api.dto.product.BrandDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.product.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper extends BaseMapper<Brand, BrandDTO> {
}
