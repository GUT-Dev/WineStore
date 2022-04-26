package com.winestore.api.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserAuthRequest {
    private final String email;
    private final String password;
}
