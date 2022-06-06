package com.winestore.api.controller.user;

import com.winestore.api.dto.user.ResetPasswordDTO;
import com.winestore.api.dto.user.UserDTO;
import com.winestore.api.mapper.user.UserMapper;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping("/names")
    public List<String> getUserNames(String name) {
        return service.getUserNames(name);
    }

    @GetMapping("/load")
    public UserDTO loadUser() {
        return mapper.toDTO(service.getPrincipal());
    }

    @PutMapping
    public String update(@RequestBody UserDTO dto) {
        return service.updateDetails(mapper.toEntity(dto));
    }

    @PutMapping("/reset-password")
    public UserDTO resetPassword(@RequestBody ResetPasswordDTO dto) {
        return mapper.toDTO(service.resetPassword(dto));
    }
}
