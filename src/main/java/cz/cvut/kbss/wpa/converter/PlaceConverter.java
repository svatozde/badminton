/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.converter;

import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.service.api.PlaceService;
import java.util.HashMap;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author zdeněk
 */
@FacesConverter(value = "placeConv")
@Configurable
public class PlaceConverter implements Converter{

    
    @Autowired
    private PlaceService placeService;
    
    private static HashMap<String, PlaceDTO> cache = new HashMap<String, PlaceDTO>();
    
     @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        if(value == null) return null;
        PlaceDTO ret = cache.get(value);
        if(ret == null){
          ret = placeService.getPlaceByName(value);
          cache.put(value, ret);
        }
        return ret;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value==null) return "";
        PlaceDTO p = (PlaceDTO) value;
        return p.getName();
    }
  
    
}
