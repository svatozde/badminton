/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.api;

import cz.cvut.kbss.wpa.dto.ProposalDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
public interface ProposalService {
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void createProposal(ProposalDTO p);
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void agreedProposal(ProposalDTO p);
    
    @Secured({"ROLE_PLAYER", "ROLE_ADMIN"})
    @Transactional
    public void deleteProposal(ProposalDTO p);
}
