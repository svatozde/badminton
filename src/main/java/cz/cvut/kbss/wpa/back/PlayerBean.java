/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("player")
@Scope("request")
public class PlayerBean {
    
    @Autowired
    private PlayerService playerService;
    
    
    private PlayerDTO player = new PlayerDTO();

    
    
    public void save(){
        playerService.createPlayer(player);
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
    public PlayerDTO getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public String setPlayer(PlayerDTO player) {
        this.player = player;
        return "index";
    }
    
}
