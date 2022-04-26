package com.winestore.config.auth;

//@Component
//@RequiredArgsConstructor
public class ApiKeyProvider
//    implements AuthenticationProvider
{
//
//    private final UserRepository repository;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String apiKey = String.valueOf(authentication.getCredentials());
//
//        User principal = repository.findByApiKey(apiKey);
//
//        if (principal == null) {
//            throw new BadCredentialsException("User not found for this API key: " + apiKey);
//        }
//
//        List<? extends GrantedAuthority> roles = principal.getRoles().stream()
//            .map(role -> new SimpleGrantedAuthority(role.name()))
//            .toList();
//
//        ApiKeyToken token = new ApiKeyToken(roles, apiKey);
//        token.authenticate(principal);
//        return token;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
}
