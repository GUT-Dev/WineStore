package com.winestore.api.controller.user;

import com.winestore.api.dto.user.UserAuthRequest;
import com.winestore.api.dto.user.UserBaseDTO;
import com.winestore.api.dto.user.UserRegistrationDTO;
import com.winestore.api.mapper.user.UserMapper;
import com.winestore.config.auth.JwtProvider;
import com.winestore.domain.entity.user.User;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final UserMapper mapper;
    private final JwtProvider jwtProvider;

    @PostMapping("/registration")
    public String registration(@RequestBody UserRegistrationDTO dto) {
        User user = service.registration(mapper.toEntity(dto));
        return user.getApiKey();
    }

    @PostMapping("/auth")
    public String auth(@RequestBody UserAuthRequest dto) {
        User user = service.findByAuthRequest(dto);
        return jwtProvider.generateToken(user.getEmail());
    }

    @GetMapping("/principal")
    public UserBaseDTO getPrincipal() {
        return mapper.toBaseDTO(service.getPrincipal());
    }
}
