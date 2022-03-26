package com.winestore.service.user;

import com.winestore.domain.entity.user.User;
import com.winestore.service.BaseService;

public interface UserService extends BaseService<User> {
    User getById(Long id);
}
