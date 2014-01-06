/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.security;

import cz.cvut.kbss.wpa.dto.UserDTO;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author zdeněk
 */
public class CurrentUserDetails extends AbstractAuthenticationToken implements UserDetails,Serializable{

   private static final long serialVersionUID = 1L;
 
   private UserDTO userDto;
   
   public CurrentUserDetails(Collection<? extends GrantedAuthority> authorities) {
       super(authorities);
   }

  
   

    /**
     * @return the userDto
     */
    public UserDTO getUserDto() {
        return userDto;
    }

    /**
     * @param userDto the userDto to set
     */
    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public Object getCredentials() {
       return userDto.getPassword();
    }

    public Object getPrincipal() {
        return userDto.getUsername();
    }

    public String getPassword() {
      return userDto.getPassword();
    }

    public String getUsername() {
       return userDto.getUsername();
    }

    public boolean isAccountNonExpired() {
      return true;
    }

    public boolean isAccountNonLocked() {
       return true;
    }

    public boolean isCredentialsNonExpired() {
       return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
    

