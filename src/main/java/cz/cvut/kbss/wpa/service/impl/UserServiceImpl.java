/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service.impl;

import cz.cvut.kbss.wpa.dao.GenericDAOIface;
import cz.cvut.kbss.wpa.dto.AdminDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import cz.cvut.kbss.wpa.model.Admin;
import cz.cvut.kbss.wpa.model.Player;
import cz.cvut.kbss.wpa.model.User;
import cz.cvut.kbss.wpa.service.api.UserService;
import java.io.Serializable;
import java.util.List;
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

    public UserServiceImpl() {
    }

    public UserDTO getUserByName(String name) {
       List<User> users = genericDAOIface.getByProperty("username", name, User.class);
       if (users.isEmpty()) {
           return null;
       }
       else {
           return map(users.get(0));
       }
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
           pdto.setWeigth(p.getWeigth());
           pdto.setHeight(p.getHeight());
           pdto.setDateOfBirth(p.getDateOfBirth());
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
