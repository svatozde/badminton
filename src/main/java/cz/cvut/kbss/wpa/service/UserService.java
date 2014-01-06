/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
public interface UserService {

    @Transactional(readOnly = true)
    public UserDTO getUserByName(String name);

}
