/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author zdeněk
 */
@Entity
public class Enroll extends AbstractEntity {
    
    @ManyToOne
    private Player player;
   
    @ManyToOne
    private League league;
    
    @Temporal(TemporalType.DATE)
    private Date paid;

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
   
    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the league
     */
    public League getLeague() {
        return league;
    }

    /**
     * @param league the league to set
     */
    public void setLeague(League league) {
        this.league = league;
    }

    /**
     * @return the paid
     */
    public Date getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(Date paid) {
        this.paid = paid;
    }
}
