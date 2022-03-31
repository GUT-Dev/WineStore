package com.winestore.api.dto.product;

import com.winestore.api.dto.user.UserBaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerReviewDTO {
    private UserBaseDTO user;
    private String message;
    private int rating;
}
