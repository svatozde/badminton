/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.rest;

import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.service.api.LeagueService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Path("/league")
@Component
public class RLeagueService {
    
    @Autowired
    private LeagueService leagueService;
    
    public RLeagueService(){
    }
    
    @GET
    @Path("/getAll")
    public List<LeagueDTO> getAllLeagues(){
        return leagueService.getAllLeagues();
    }
    @GET
    @Path("/getEnrolled")
    public List<LeagueDTO> getEnroledLeaues(PlayerDTO p){
        return leagueService.getEnroledLeagues(p);
    }
    
    @GET
    @Path("/getMatches")
    public LeagueDTO getLeagueMatches(LeagueDTO l){
        return leagueService.getMatches(l);
    }
    
}
