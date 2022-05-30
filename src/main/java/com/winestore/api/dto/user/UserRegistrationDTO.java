package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
}
