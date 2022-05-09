package com.winestore.service.user.impl;

import com.winestore.api.dto.user.UserAuthRequest;
import com.winestore.domain.entity.user.CustomUserDetails;
import com.winestore.domain.entity.user.User;
import com.winestore.domain.repository.user.UserRepository;
import com.winestore.enums.Role;
import com.winestore.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.security.InvalidParameterException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Set<Role> defaultRole = Set.of(Role.CUSTOMER);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUserDetails findByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        } else {
            return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(defaultRole);

        return userRepository.save(user);
    }

    @Override
    public User findByAuthRequest(UserAuthRequest dto) {
        User user = userRepository.findByEmail(dto.getEmail());

        if (user == null) {
            throw new EntityNotFoundException("User with email " + dto.getEmail() + " not found");
        } else if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidParameterException("Password doesn't correct");
        } else if (!user.isEnabled()) {
            throw new AccessDeniedException("User is blocked");
        } else if (user.isBaned()) {
            throw new AccessDeniedException("User is baned with reason: " + user.getBanReason());
        } else {
            return user;
        }
    }

    @Override
    public User getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomUserDetails customUserDetails = (CustomUserDetails) principal;
        return userRepository.findByEmail(customUserDetails.getUsername());
    }

    @Override
    public Long getPrincipalId() {
        return getPrincipal().getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
