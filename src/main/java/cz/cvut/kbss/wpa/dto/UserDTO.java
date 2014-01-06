/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

/**
 *
 * @author jan
 */
public class UserDTO extends AbstractDTO{
    
    protected String username;
    protected String password;

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
    
    
}
