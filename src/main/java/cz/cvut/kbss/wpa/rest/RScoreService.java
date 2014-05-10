/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.rest;

import com.sun.jersey.api.spring.Autowire;
import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.service.api.ScoreService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Path("/score")
@Component
public class RScoreService {

    @Autowired
    private ScoreService scoreService;
    
    

    @POST
    @Path("/add")
    public MatchDTO addSet(MatchDTO m, SetDTO s) {
        scoreService.addSet(m, s);
        return m;
    }

    @POST
    @Path("/update")
    public MatchDTO updateSet(MatchDTO m, SetDTO s) {
        scoreService.updateSet(m, s);
        return m;
    }

    @POST
    @Path("/delete")
    public MatchDTO deleteSet(MatchDTO m, SetDTO s) {
       scoreService.deleteSet(m, s);
       return m;
    }

}
