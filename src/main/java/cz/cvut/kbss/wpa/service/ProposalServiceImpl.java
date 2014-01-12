/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dao.GenericHibernateJpaDAO;
import cz.cvut.kbss.wpa.dto.ProposalDTO;
import cz.cvut.kbss.wpa.model.Match;
import cz.cvut.kbss.wpa.model.Place;
import cz.cvut.kbss.wpa.model.Player;
import cz.cvut.kbss.wpa.model.Proposal;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service("proposalService")
public class ProposalServiceImpl implements ProposalService, Serializable {

    @Autowired
    private GenericHibernateJpaDAO genericHibernateJpaDAO;
    
    public void createProposal(ProposalDTO p) {
       Match m = genericHibernateJpaDAO.getById(p.getMatch().getId(), Match.class);
       Player pl = genericHibernateJpaDAO.getById(p.getPlayer().getId(), Player.class);
       Place plc = genericHibernateJpaDAO.getById(p.getPlace().getId(), Place.class);
       Proposal prop = new Proposal();
       prop.setMatch(m);
       prop.setPlace(plc);
       prop.setPlayer(pl);
       prop.setPropDate(p.getPropDate());
       prop = genericHibernateJpaDAO.saveOrUpdate(prop);
       p.setId(prop.getId());
    }

    public void agreedProposal(ProposalDTO p) {
       Proposal pr =  genericHibernateJpaDAO.getById(p.getId(), Proposal.class);
       pr.setMatchAgreed(true);
       genericHibernateJpaDAO.saveOrUpdate(pr);
       p.setAgreed(Boolean.TRUE);
    }

    public void deleteProposal(ProposalDTO p) {
        genericHibernateJpaDAO.removeById(p.getId(), Proposal.class);
    }
    
    
}
