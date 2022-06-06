package com.winestore.service.user;

import com.winestore.api.dto.user.ResetPasswordDTO;
import com.winestore.api.dto.user.UserAuthRequest;
import com.winestore.domain.entity.user.CustomUserDetails;
import com.winestore.domain.entity.user.User;

import java.util.List;

public interface UserService {
    CustomUserDetails findByEmail(String email);

    User getById(Long id);

    User update(User user);

    String updateDetails(User user);

    User registration(User user);

    User findByAuthRequest(UserAuthRequest dto);

    User getPrincipal();

    Long getPrincipalId();

    List<String> getUserNames(String name);

    User resetPassword(ResetPasswordDTO dto);
}
