/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("user")
@Scope("session")
public class UserSessionBean {
    
    @Autowired
    private UserService userService;
    
    private List<LeagueDTO> erolledLeagues;
    
}
