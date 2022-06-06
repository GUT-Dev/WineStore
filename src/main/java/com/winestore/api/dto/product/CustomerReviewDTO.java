package com.winestore.api.dto.product;

import com.winestore.api.dto.user.UserDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerReviewDTO {
    private UserDTO user;
    private String message;
    private int rating;
}
