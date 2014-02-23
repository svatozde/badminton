/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.PlayerCreateDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.service.api.PlayerService;
import cz.cvut.kbss.wpa.service.api.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("player")
@RequestScoped
public class PlayerBean {
    
    @Autowired
    private PlayerService playerService;
    
    @Autowired
    private UserService userService;
    
    private PlayerCreateDTO player = new PlayerCreateDTO();
    
    public void save(){
        playerService.createPlayer(player);
    }
    
    public void checkUniqueUsername(FacesContext context, 
            UIComponent toValidate, Object value)
    {
        String username = (String) value;
        if (userService.getUserByName(username) != null) {
            ((UIInput)toValidate).setValid(false);
            String message = "Uzivatel jiz existuje";
            FacesMessage errorMessage = new FacesMessage(message);
            errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(toValidate.getClientId(context),
                errorMessage
            );
        }
    }
    
    /**
     * @return the playerService
     */
    public PlayerService getPlayerService() {
        return playerService;
    }

    /**
     * @param playerService the playerService to set
     */
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * @return the player
     */
    public PlayerCreateDTO getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public String setPlayer(PlayerCreateDTO player) {
        this.player = player;
        return "index";
    }
    
}
