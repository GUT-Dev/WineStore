package com.winestore.api.mapper.product;

import com.winestore.api.dto.product.CustomerReviewDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.product.CustomerReview;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerReviewMapper extends BaseMapper<CustomerReview, CustomerReviewDTO> {
}
