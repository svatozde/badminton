/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author jan
 */
public class AdminDTO extends UserDTO{

    public AdminDTO(Long id, String username, String password) {
        super(id, username, password);
    }

    public AdminDTO() {
        super(null, null, null);
    }
    
    @Override
    public List<GrantedAuthority> getGrantedAuthorities() {
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
         auth.add(new GrantedAuthorityImpl("ROLE_PLAYER"));
         auth.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
         return auth;
    }
    
}
