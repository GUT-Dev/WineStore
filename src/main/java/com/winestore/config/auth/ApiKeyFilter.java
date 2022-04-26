package com.winestore.config.auth;

//public class ApiKeyFilter extends AbstractAuthenticationProcessingFilter {
//
//    private static final String API_KEY_HEADER_NAME = "api-key";
//
//    private static OrRequestMatcher matchers;
//    private static final Set<String> matchersValue;
//
//    static {
//        matchersValue = new HashSet<>();
//        matchersValue.add("/cart/**");
//
//        List<RequestMatcher> matchers = new ArrayList<>();
//        matchersValue.forEach(
//            m -> matchers.add(new AntPathRequestMatcher(m))
//        );
//
//        ApiKeyFilter.matchers = new OrRequestMatcher(matchers);
//    }
//
//    public ApiKeyFilter(AuthenticationManager authenticationManager) {
//        super(matchers, authenticationManager);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        ApiKeyToken token = new ApiKeyToken(AuthorityUtils.NO_AUTHORITIES, request.getHeader(API_KEY_HEADER_NAME));
//        return getAuthenticationManager().authenticate(token);
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//
//    public static Set<String> getMatchers() {
//        return matchersValue;
//    }
//}
