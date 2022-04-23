package com.winestore.service.user;

import com.winestore.api.dto.user.UserAuthDTO;
import com.winestore.domain.entity.user.User;

public interface UserService {
    User findByApiKey(String username);
    
    User getById(Long id);

    User update(User user);

    User registration(User user);

    String auth(UserAuthDTO dto);
}
