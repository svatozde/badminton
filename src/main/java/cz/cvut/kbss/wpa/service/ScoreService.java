/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
public interface ScoreService {
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void addScore(MatchDTO m);
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void removeScore(MatchDTO m);
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void addSet(MatchDTO m, SetDTO s);
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void updateSet(MatchDTO m, SetDTO s);
}
