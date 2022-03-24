package com.winestore.service.user.impl;

import com.winestore.domain.entity.user.User;
import com.winestore.domain.repository.user.UserRepository;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        if (repository.existsById(user.getId())) {
            return repository.save(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public static User getPrincipal() {
        return null;
    }

    public static Long getPrincipalId() {
        return null;
    }

}
