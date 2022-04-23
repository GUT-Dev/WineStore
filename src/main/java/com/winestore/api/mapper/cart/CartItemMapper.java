package com.winestore.api.mapper.cart;

import com.winestore.api.dto.cart.CartItemDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.cart.CartItem;
import org.mapstruct.Mapper;

@Mapper
public interface CartItemMapper extends BaseMapper<CartItem, CartItemDTO> {
}
