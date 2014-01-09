/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dto.EnrollUserDTO;
import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.TableDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
public interface LeagueService {

    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void createLeague(LeagueDTO league);

    
    @Transactional
    public void startLeague(LeagueDTO league);

    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void enrollUser(EnrollUserDTO league);
    
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public List<LeagueDTO> getAllLeagues();
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public List<LeagueDTO> getEnroledLeagues(PlayerDTO dto);
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public LeagueDTO getMatches(LeagueDTO league);
    
    @Transactional
    public List<LeagueDTO> getLeaguesNotStarted();
    
    
    
    

}
