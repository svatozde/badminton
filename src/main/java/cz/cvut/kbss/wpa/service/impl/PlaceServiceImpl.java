/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.impl;

import cz.cvut.kbss.wpa.dao.GenericDAOIface;
import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.model.Place;
import cz.cvut.kbss.wpa.service.api.PlaceService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service("placeService")
public class PlaceServiceImpl implements PlaceService, Serializable{

    @Autowired
    private GenericDAOIface genericDAOIface; 

    public PlaceServiceImpl() {
    }
    
    
    public void createPlace(PlaceDTO place) {
       Place p = new Place();
       p.setName(place.getName());
       p.setAdress(place.getAdress());
       genericDAOIface.saveOrUpdate(p);
    }
    
      public List<PlaceDTO> getAllPlaces() {
         List<Place> places = genericDAOIface.getAll(Place.class);
         List<PlaceDTO> dtos = new ArrayList<PlaceDTO>();
         for(Place p : places) dtos.add(remap(p));
         return dtos;
      }
      
       public PlaceDTO getPlaceByName(String name) {
        return remap(genericDAOIface.getByPropertyUnique("name", name, Place.class));
      }
      
      
      
     private PlaceDTO remap(Place p){
         if(p == null) return null;
         PlaceDTO ret = new PlaceDTO();
         ret.setName(p.getName());
         ret.setAdress(p.getAdress());
         ret.setId(p.getId());
         return ret;
     }
    

    /**
     * @return the genericDAOIface
     */
    public GenericDAOIface getGenericDAOIface() {
        return genericDAOIface;
    }

    /**
     * @param genericDAOIface the genericDAOIface to set
     */
    public void setGenericDAOIface(GenericDAOIface genericDAOIface) {
        this.genericDAOIface = genericDAOIface;
    }

  
}
