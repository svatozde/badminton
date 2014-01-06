/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

/**
 *
 * @author zdeněk
 */
public class TableDTO extends AbstractDTO{
    
    private PlayerDTO[] players;
    
    private MatchDTO[][] mathces;

    /**
     * @return the mathces
     */
    public MatchDTO[][] getMathces() {
        return mathces;
    }

    /**
     * @param mathces the mathces to set
     */
    public void setMathces(MatchDTO[][] mathces) {
        this.mathces = mathces;
    }

    /**
     * @return the players
     */
    public PlayerDTO[] getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(PlayerDTO[] players) {
        this.players = players;
    }
    
}
