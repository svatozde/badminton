/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dao.GenericDAOIface;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.model.Player;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerService, Serializable{

    @Autowired
    private GenericDAOIface genericDAOIface;

    public void createPlayer(PlayerDTO player) {
        Player p = new Player();
        p.setName(player.getName());
        p.setUsername(player.getUsername());
        p.setUsername(player.getUsername());
        p.setPassword(player.getPassword());
        p.setDateOfBirth(player.getDateOfBirth());
        genericDAOIface.saveOrUpdate(p);
    }
    
}
