package com.winestore.api.mapper.user;

import com.winestore.api.dto.user.AddressDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.user.Address;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper extends BaseMapper<Address, AddressDTO> {
}
