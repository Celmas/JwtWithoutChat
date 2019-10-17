package ru.itis.longpolling.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenHelper jwtTokenHelper;

    public JwtTokenFilterConfigurer(JwtTokenHelper jwtTokenHelper) {
        this.jwtTokenHelper = jwtTokenHelper;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenHelper);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
