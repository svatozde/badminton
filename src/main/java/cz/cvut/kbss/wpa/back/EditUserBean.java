/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.AdminDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.PlayerUpdateDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import cz.cvut.kbss.wpa.security.CurrentUserDetails;
import cz.cvut.kbss.wpa.service.api.AdminService;
import cz.cvut.kbss.wpa.service.api.PlayerService;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan
 */
@Component("editUser")
@ViewScoped
public class EditUserBean {
    
    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private AdminService adminService;
    
    private PlayerUpdateDTO uPlayer;
    
    private PlayerDTO player;
    
    private AdminDTO admin;
    
    public AdminDTO getAdmin() {
        if (admin == null) {
            //load from credential
            CurrentUserDetails cud = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            admin = (AdminDTO)cud.getUserDto();
            admin.setPassword("");
        }
        return admin;
    }

    public PlayerDTO getPlayer() {
        if (player == null) {
            //load from credential
            CurrentUserDetails cud = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            player = (PlayerDTO)cud.getUserDto();
            player.setPassword("");
        }
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }
    
    public void save()
    {
        if (player != null) {
            playerService.updatePlayer(uPlayer);
        }
        if (admin != null) {
            adminService.updateAdmin(admin);
        }
    }
    
}
