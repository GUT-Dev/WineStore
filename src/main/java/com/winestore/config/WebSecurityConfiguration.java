package com.winestore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/css/**", "/registration", "/images/**", "/wine/**", "/error").permitAll()
            .antMatchers("/admin/**").hasAuthority("ADMIN")
            .antMatchers("/new", "/edit/**").hasAnyAuthority("MANAGER")
            .anyRequest().authenticated()
            .and()
            .formLogin().defaultSuccessUrl("/wine", true)
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
        ;
    }
}
