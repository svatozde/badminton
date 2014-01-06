/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.Date;

/**
 *
 * @author jan
 */
public class EnrollDTO extends AbstractDTO{
    protected PlayerDTO player;
    protected LeagueDTO league;
    protected Date paid;

    public EnrollDTO(Long id, PlayerDTO player, LeagueDTO league, Date paid) {
        super(id);
        this.player = player;
        this.league = league;
        this.paid = paid;
    }

    public EnrollDTO() {
    }
    
    

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public LeagueDTO getLeague() {
        return league;
    }

    public void setLeague(LeagueDTO league) {
        this.league = league;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }
}
