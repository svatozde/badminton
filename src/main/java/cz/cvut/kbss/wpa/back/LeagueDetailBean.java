/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.service.api.LeagueService;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("leagueDetail")
@RequestScoped
public class LeagueDetailBean {
    
    private LeagueDTO currentLeagueMatches;
    
    @Autowired
    private LeagueService leagueService;
    
     public String leagueDetail(LeagueDTO dto) {
        setCurrentLeagueMatches(getLeagueService().getMatches(dto));
        return "leagueDetail";
    }
     
      public String getStyle(Integer i, Integer j) {
       if(i.equals(j)){
           return "tableCellDark";
       }else if ( ((i + j) % 2) == 1){
           return "tableCellBlue";
       }
       else if(((i + j) % 2) == 0){
           return "tableCellGray";
       }
       return "";
    }

    /**
     * @return the currentLeagueMatches
     */
    public LeagueDTO getCurrentLeagueMatches() {
        return currentLeagueMatches;
    }

    /**
     * @param currentLeagueMatches the currentLeagueMatches to set
     */
    public void setCurrentLeagueMatches(LeagueDTO currentLeagueMatches) {
        this.currentLeagueMatches = currentLeagueMatches;
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
}
