package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResetPasswordDTO {
    private final String oldPassword;
    private final String newPassword;
}
