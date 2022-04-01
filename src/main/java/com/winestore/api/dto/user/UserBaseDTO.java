package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBaseDTO {
    private Long id;
    private String img;
    private String firstName;
    private String lastName;
}
