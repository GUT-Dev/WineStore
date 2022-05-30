package com.winestore.api.mapper.product;

import com.winestore.api.dto.product.AddReviewDTO;
import com.winestore.api.dto.product.CustomerReviewDTO;
import com.winestore.domain.entity.product.CustomerReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CustomerReviewMapper {

    @Mapping(target = "user", source = "id.user")
    CustomerReviewDTO toDTO(CustomerReview entity);

    @Mapping(target = "id.wine.id", source = "wineId")
    CustomerReview toEntity(AddReviewDTO dto);
}
