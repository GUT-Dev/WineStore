package com.winestore.api.controller.user;

import com.winestore.api.dto.user.UserAuthDTO;
import com.winestore.api.dto.user.UserRegistrationDTO;
import com.winestore.api.mapper.user.UserMapper;
import com.winestore.domain.entity.user.User;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping("/registration")
    public String registration(@RequestBody UserRegistrationDTO dto) {
        User user = service.registration(mapper.toEntity(dto));
        return user.getApiKey();
    }

    @PostMapping("/auth")
    public String getForm(@RequestBody UserAuthDTO dto) {
        return service.auth(dto);
    }
}
