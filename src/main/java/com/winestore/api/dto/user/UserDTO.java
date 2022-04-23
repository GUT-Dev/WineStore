package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends UserBaseDTO {
    private String phoneNumber;
    private String createdAt;
}
