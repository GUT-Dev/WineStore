package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthDTO {
    private String email;
    private String password;
}