package com.winestore.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {
    private int errorCode;
    private String message;
}
