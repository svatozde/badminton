/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.service.LeagueService;
import java.util.List;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service
public class LeagueStaringBean  extends QuartzJobBean  {
    
    @Autowired
    private LeagueService leagueService;
    
    @Override
    protected void executeInternal(JobExecutionContext jec) throws JobExecutionException {
        List<LeagueDTO> ls = leagueService.getLeaguesNotStarted();
        for(LeagueDTO l : ls){
            leagueService.startLeague(l);
        }
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
