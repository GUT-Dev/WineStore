package com.winestore.service.user.impl;

import com.winestore.domain.entity.user.User;
import com.winestore.domain.repository.user.UserRepository;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByApiKey(String username) throws UsernameNotFoundException {
        User user = userRepository.findByApiKey(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return user;
        }
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public User create(User user) {
        user.setPassword(user.encodePassword(user.encodePassword(user.getPassword())));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
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
