/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.security;

import cz.cvut.kbss.wpa.provider.HashProvider;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("customAuthenticationProvider")
public class CustomSecurityProvider implements AuthenticationProvider, Serializable {

    @Autowired
    private UserDetailsService userService;

    @Autowired
    private HashProvider hashProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();

        String password = authentication.getCredentials().toString();

        UserDetails user = userService.loadUserByUsername(name);

        
        CurrentUserDetails ret = null;
        
        if (user == null || !user.getPassword().equals(hashProvider.computeHash(password))) {
            throw new BadCredentialsException("Username not found.");
        }
        
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
