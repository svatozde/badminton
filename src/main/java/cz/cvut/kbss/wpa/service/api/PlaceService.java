/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service.api;

import cz.cvut.kbss.wpa.dto.PlaceDTO;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
public interface PlaceService {

    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void createPlace(PlaceDTO place);

    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public List<PlaceDTO> getAllPlaces();

    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional(readOnly = true)
    public PlaceDTO getPlaceByName(String name);

}
