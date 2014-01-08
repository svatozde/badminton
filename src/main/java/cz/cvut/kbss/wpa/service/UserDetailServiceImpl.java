/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dto.AdminDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import cz.cvut.kbss.wpa.security.CurrentUserDetails;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
@Repository
public class UserDetailServiceImpl implements UserDetailsService, Serializable {

    @Autowired
    private UserService userService;

    @Transactional
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        UserDTO dto;
        dto = userService.getUserByName(string);
        if (dto == null) {
            throw new UsernameNotFoundException("Username " + string + " does not exist");//TODO add transaltion
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(dto.getGrantedAuthority());
        CurrentUserDetails curr = new CurrentUserDetails(authorities);
        curr.setUserDto(dto);
        return curr;
    }

}
