/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.service.PlaceService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("place")
@Scope("request")
public class PlaceBean {

    @Autowired
    private PlaceService placeService;

    private PlaceDTO place = new PlaceDTO();

    public List<SelectItem> getAllPlaces() {
        List<SelectItem> allCourses = new ArrayList<SelectItem>();
        for (PlaceDTO c : placeService.getAllPlaces()) {
            allCourses.add(new SelectItem(c, c.getName()));
        }
        return allCourses;
    }

    public String save() {
        placeService.createPlace(place);
        return "index";
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

    /**
     * @return the place
     */
    public PlaceDTO getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(PlaceDTO place) {
        this.place = place;
    }

}
