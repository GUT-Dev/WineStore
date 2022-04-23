package com.winestore.config;

import com.winestore.config.auth.ApiKeyFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors()
            .and()
            .authorizeRequests()
            .antMatchers(ApiKeyFilter.getMatchers().toArray(String[]::new)).authenticated()
//            .antMatchers("/css/**", "/registration", "/images/**", "/wine/**", "/error").permitAll()
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            .antMatchers("/new", "/edit/**").hasAnyAuthority("MANAGER")
            .anyRequest().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .addFilterBefore(new ApiKeyFilter(authenticationManager()), AnonymousAuthenticationFilter.class)
        ;
    }
}
