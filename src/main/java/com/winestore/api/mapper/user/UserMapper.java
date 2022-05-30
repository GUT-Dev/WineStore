package com.winestore.api.mapper.user;

import com.winestore.api.dto.user.UserBaseDTO;
import com.winestore.api.dto.user.UserDTO;
import com.winestore.api.dto.user.UserRegistrationDTO;
import com.winestore.api.mapper.BaseMapper;
import com.winestore.domain.entity.user.User;
import org.mapstruct.Mapper;

@Mapper(uses = {AddressMapper.class})
public interface UserMapper extends BaseMapper<User, UserDTO> {
    UserBaseDTO toBaseDTO(User user);

    User toEntity(UserRegistrationDTO dto);
}
