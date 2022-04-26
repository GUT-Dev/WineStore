package com.winestore.config.auth;

//@Transient
//public class ApiKeyToken extends AbstractAuthenticationToken {
//
//    @Serial
//    private static final long serialVersionUID = -1106548032990041529L;
//
//    private User user;
//    private final String apiKey;
//
//    public ApiKeyToken(Collection<? extends GrantedAuthority> authorities, String apiKey) {
//        super(authorities);
//        this.apiKey = apiKey;
//        setAuthenticated(false);
//    }
//
//    public void authenticate(User user) {
//        this.user = user;
//        setAuthenticated(true);
//    }
//
//    @Override
//    public Object getCredentials() {
//        return apiKey;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return user;
//    }
//}
