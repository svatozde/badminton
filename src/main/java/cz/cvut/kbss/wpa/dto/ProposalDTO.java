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
public class ProposalDTO extends AbstractDTO{
    
    protected PlaceDTO place;
    protected MatchDTO match;
    protected PlayerDTO player;
    protected Date propDate;
    private Boolean agreed;

    public ProposalDTO(Long id, PlaceDTO place, MatchDTO match, PlayerDTO player, Date propDate) {
        super(id);
        this.place = place;
        this.match = match;
        this.player = player;
        this.propDate = propDate;
    }

    public ProposalDTO() {
    }

    public PlaceDTO getPlace() {
        return place;
    }

    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

    public MatchDTO getMatch() {
        return match;
    }

    public void setMatch(MatchDTO match) {
        this.match = match;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public Date getPropDate() {
        return propDate;
    }

    public void setPropDate(Date propDate) {
        this.propDate = propDate;
    }

    /**
     * @return the agreed
     */
    public Boolean getAgreed() {
        return agreed;
    }

    /**
     * @param agreed the agreed to set
     */
    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }
    
}
