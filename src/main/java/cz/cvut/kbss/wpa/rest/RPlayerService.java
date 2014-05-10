package cz.cvut.kbss.wpa.rest;

import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.PlayerUpdateDTO;
import cz.cvut.kbss.wpa.service.api.PlayerService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import org.springframework.beans.factory.annotation.Autowired;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Path("/player")
@Component
public class RPlayerService {

    @Autowired
    private PlayerService playerService;

    public RPlayerService(){
    }
    
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlayerDTO getPlayer(@PathParam("id") Long id) {
        return playerService.getPlayer(id);
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public void updatePlayer(PlayerUpdateDTO dto) {
         playerService.updatePlayer(dto);
    }
    

    /**
     * @return the playerService
     */
    public PlayerService getPlayerService() {
        return playerService;
    }

    /**
     * @param playerService the playerService to set
     */
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

}
