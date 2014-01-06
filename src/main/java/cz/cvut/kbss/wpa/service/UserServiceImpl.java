/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dao.GenericDAOIface;
import cz.cvut.kbss.wpa.dto.AdminDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import cz.cvut.kbss.wpa.model.Admin;
import cz.cvut.kbss.wpa.model.Player;
import cz.cvut.kbss.wpa.model.User;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

    @Autowired
    private GenericDAOIface genericDAOIface;

    public UserDTO getUserByName(String name) {
       User u = genericDAOIface.getByPropertyUnique("username", name, User.class);
       return map(u);
    }
    
    private UserDTO map(User u){
        UserDTO dto = null;
        if(u instanceof Admin){
           dto = new AdminDTO();
        }else if(u instanceof Player){
           Player p = (Player) u;
           PlayerDTO pdto = new PlayerDTO();
           pdto.setName(p.getName());
           pdto.setSurname(p.getSurname());
           dto = pdto;
        }else{
            dto = new UserDTO();
        }
        
        dto.setPassword(u.getPassword());
        dto.setUsername(u.getUsername());
        dto.setId(u.getId());
    
        return dto;
    
    }

    /**
     * @return the genericDAOIface
     */
    public GenericDAOIface getGenericDAOIface() {
        return genericDAOIface;
    }

    /**
     * @param genericDAOIface the genericDAOIface to set
     */
    public void setGenericDAOIface(GenericDAOIface genericDAOIface) {
        this.genericDAOIface = genericDAOIface;
    }

  

}
