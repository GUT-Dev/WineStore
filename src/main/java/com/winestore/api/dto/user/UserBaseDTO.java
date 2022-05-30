package com.winestore.api.dto.user;

import com.winestore.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserBaseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Role> roles;
}
