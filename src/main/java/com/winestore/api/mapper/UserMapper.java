package com.winestore.api.mapper;

import com.winestore.api.dto.user.UserBaseDTO;
import com.winestore.api.dto.user.UserDTO;
import com.winestore.domain.entity.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDTO> {
    UserBaseDTO toBaseDTO(User user);
}
