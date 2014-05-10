/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.rest;

import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.model.Place;
import cz.cvut.kbss.wpa.service.api.PlaceService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Path("/player")
@Component
public class RPlaceService {
    @Autowired
    private PlaceService placeService;
    
    public RPlaceService(){
    }
    
    @GET
    @Path("/getAll")
    public  List<PlaceDTO> getPlaces(){
        return placeService.getAllPlaces();
    }
    
    @GET
    @Path("/get")
    public PlaceDTO getPlace(String name){
        return placeService.getPlaceByName(name);
    }
    
    
}
