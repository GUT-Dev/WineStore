package com.winestore.config.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApiKeyFilter extends AbstractAuthenticationProcessingFilter {

    private static final String API_KEY_HEADER_NAME = "api-key";

    private static OrRequestMatcher matchers;
    private static final Set<String> matchersValue;

    static {
        matchersValue = new HashSet<>();
        matchersValue.add("/cart");

        List<RequestMatcher> matchers = new ArrayList<>();
        matchersValue.forEach(
            m -> matchers.add(new AntPathRequestMatcher(m))
        );

        ApiKeyFilter.matchers = new OrRequestMatcher(matchers);
    }

    public ApiKeyFilter(AuthenticationManager authenticationManager) {
        super(matchers, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ApiKeyToken token = new ApiKeyToken(AuthorityUtils.NO_AUTHORITIES, request.getHeader(API_KEY_HEADER_NAME));
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
    }

    public static Set<String> getMatchers() {
        return matchersValue;
    }
}
