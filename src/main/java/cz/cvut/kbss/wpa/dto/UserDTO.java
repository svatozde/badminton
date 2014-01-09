/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import cz.cvut.kbss.wpa.provider.HashProvider;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author jan
 */
public class UserDTO extends AbstractDTO{
    
    protected String username;
    protected String password;
    
     @Autowired
    private transient HashProvider hashProvider;

    public UserDTO(Long id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    public UserDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public boolean hasPassword(String password){
        return hashProvider.computeHash(password).equals(this.password);
    }
     
     /**
     * @return the provider
     */
    public HashProvider getProvider() {
        return hashProvider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(HashProvider provider) {
        this.hashProvider = provider;
    }
    
    
     public List<GrantedAuthority> getGrantedAuthorities()
     {
         List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
         auth.add(new GrantedAuthorityImpl("ROLE_USER"));
         return auth;
     }
    
    
}
