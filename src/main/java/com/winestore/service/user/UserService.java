package com.winestore.service.user;

import com.winestore.domain.entity.user.User;

public interface UserService {
    User findByApiKey(String username);

    User getById(Long id);

    User create(User user);

    User update(User user);
}
