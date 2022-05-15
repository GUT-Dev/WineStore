package com.winestore.api.dto.cart;

import com.winestore.api.dto.user.UserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class CartTrackingDTO extends CartHistoryDTO {
    private UserDTO user;
}
