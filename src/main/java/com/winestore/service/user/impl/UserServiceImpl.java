package com.winestore.service.user.impl;

import com.winestore.api.dto.user.UserAuthDTO;
import com.winestore.domain.entity.user.User;
import com.winestore.domain.repository.user.UserRepository;
import com.winestore.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;

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
    public User update(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public User registration(User user) {
        user.setEnabled(true);
        user.setPassword(user.encodePassword(user.getPassword()));
        user.setApiKey(user.getPassword());

        return userRepository.save(user);
    }

    @Override
    public String auth(UserAuthDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            throw new EntityNotFoundException("User with email " + dto.getEmail() + " not found");
        } else if (!user.getPassword().equals(user.encodePassword(dto.getPassword()))) {
            throw new InvalidParameterException("Password doesn't correct");
        } else if (!user.isEnabled()) {
            throw new AccessDeniedException("User is blocked");
        } else if (user.isBaned()) {
            throw new AccessDeniedException("User is baned with reason: " + user.getBanReason());
        } else {
            return user.getApiKey();
        }
    }

    public static User getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User) principal;
    }

    public static Long getPrincipalId() {
        return getPrincipal().getId();
    }
}
