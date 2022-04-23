package com.winestore.config.auth;

import com.winestore.domain.entity.user.User;
import com.winestore.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiKeyProvider implements AuthenticationProvider {

    private final UserRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String apiKey = String.valueOf(authentication.getCredentials());

        User principal = repository.findByApiKey(apiKey);

        if (principal == null) {
            throw new BadCredentialsException("User not found for this API key: " + apiKey);
        }

        ApiKeyToken token = new ApiKeyToken(AuthorityUtils.NO_AUTHORITIES, apiKey);
        token.authenticate(principal);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
