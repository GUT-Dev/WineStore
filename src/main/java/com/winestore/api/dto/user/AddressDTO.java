package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private Long id;
    private String postCode;
    private String land;
    private String city;
    private String street;
    private String homeNumber;
}
