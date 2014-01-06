/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.service.LeagueService;
import cz.cvut.kbss.wpa.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("league")
@Scope("request")
public class LeagueBean {
    
    @Autowired
    private LeagueService leagueService;
    
    @Autowired
    private PlaceService placeService;
    
    private LeagueDTO league = new LeagueDTO();

    
    public String createLeague(){
        leagueService.createLeague(league);
        return "index";
    }
    
    /**
     * @return the league
     */
    public LeagueDTO getLeague() {
        return league;
    }

    /**
     * @param league the league to set
     */
    public void setLeague(LeagueDTO league) {
        this.league = league;
    }

    /**
     * @return the leagueService
     */
    public LeagueService getLeagueService() {
        return leagueService;
    }

    /**
     * @param leagueService the leagueService to set
     */
    public void setLeagueService(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    /**
     * @return the placeService
     */
    public PlaceService getPlaceService() {
        return placeService;
    }

    /**
     * @param placeService the placeService to set
     */
    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }
    
    
}
