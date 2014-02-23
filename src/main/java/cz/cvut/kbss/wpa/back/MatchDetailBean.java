/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.ProposalDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.security.CurrentUserDetails;
import cz.cvut.kbss.wpa.service.api.ProposalService;
import cz.cvut.kbss.wpa.service.api.ScoreService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("matchDetail")
@ViewScoped
public class MatchDetailBean {
    
    
    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ScoreService scoreService;
    
    private MatchDTO currentMatch;

    private ProposalDTO proposal;

    private SetDTO set;
    
    private List<PlaceDTO> possiblePlaces;

    public boolean isEdit() {
        for (SetDTO s : currentMatch.getSets()) {
            if (s.isEditable()) {
                return true;
            }
        }
        return false;
    }

    public void addSet() {
        scoreService.addSet(currentMatch, set);
        set = new SetDTO();
        set.setNumber(currentMatch.getSets().size() + 1);

    }
    
    public void deleteSet(SetDTO setDto)
    {
        scoreService.deleteSet(currentMatch, setDto);
    }

    public void update(SetDTO dto) {
        scoreService.updateSet(currentMatch, dto);
        for (SetDTO ss : currentMatch.getSets()) {
            ss.setEditable(false);
        }
    }

    public void setEditable(SetDTO s) {
         for (SetDTO ss : currentMatch.getSets()) {
            ss.setEditable(false);
        }
        s.setEditable(true);
    }

    public void saveScore() {
        for (SetDTO s : currentMatch.getSets()) {
            s.setEditable(false);
        }
    }

    public boolean isMatchAgerrd() {
        for (ProposalDTO p : currentMatch.getProposals1()) {
            if (Boolean.TRUE.equals(p.getAgreed())) {
                return true;
            }
        }
        for (ProposalDTO p : currentMatch.getProposals2()) {
            if (Boolean.TRUE.equals(p.getAgreed())) {
                return true;
            }
        }
        return false;
    }

    public ProposalDTO getAgreedProposal() {
        for (ProposalDTO p : currentMatch.getProposals1()) {
            if (Boolean.TRUE.equals(p.getAgreed())) {
                return p;
            }
        }
        for (ProposalDTO p : currentMatch.getProposals2()) {
            if (Boolean.TRUE.equals(p.getAgreed())) {
                return p;
            }
        }
        return null;
    }

    public void createProposal1() {
        List<ProposalDTO> props = currentMatch.getProposals1();
        if (props == null) {
            props = new ArrayList<ProposalDTO>();
        }
        props.add(proposal);
        proposalService.createProposal(proposal);
        refresh();
    }

    public void createProposal2() {
        List<ProposalDTO> props = currentMatch.getProposals2();
        if (props == null) {
            props = new ArrayList<ProposalDTO>();
        }
        props.add(proposal);
        proposalService.createProposal(proposal);
        refresh();
    }
    
    public void deleteProposal(ProposalDTO p) 
    {
        if ((proposal != null) && (proposal.getId() == p.getId())) {
            return;
        }
        //najit kterej proposal to je
        List<ProposalDTO> props1 = currentMatch.getProposals1();
        List<ProposalDTO> props2 = currentMatch.getProposals2();
        props1.remove(p);
        props2.remove(p);
        proposalService.deleteProposal(p);
    }  

    private void refresh() {
         
        CurrentUserDetails u = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(u.getUserDto() instanceof  PlayerDTO)) return;
        proposal = new ProposalDTO();
        proposal.setMatch(currentMatch);
        proposal.setPlayer((PlayerDTO) u.getUserDto());
        set = new SetDTO();
        set.setNumber(currentMatch.getSets().size() + 1);
        for (SetDTO s : currentMatch.getSets()) {
            s.setEditable(false);
        }
    }

    public List<SelectItem> getAllPlaces() {
        List<SelectItem> places = new ArrayList<SelectItem>();
        for (PlaceDTO c : possiblePlaces) {
            places.add(new SelectItem(c, c.getName()));
        }
        return places;
    }

    /**
     * data table hack
     *
     * @return
     */
    public List<ProposalDTO> getPlayer1Proposals() {
        List<ProposalDTO> p = currentMatch.getProposals1();
        return p;
    }

    /**
     * data table hack
     *
     * @return
     */
    public List<ProposalDTO> getPlayer2Proposals() {
        List<ProposalDTO> p = currentMatch.getProposals2();
        return p;
    }
    
    public boolean checkIfCanAddScore(){
         return currentMatch.getPlayed() != null && (isPlayer1() || isPlayer2());
    }
    
    public boolean checkIfCanPropose() {
        return currentMatch.getPlayed() == null && (isPlayer1() || isPlayer2());
    }

    public boolean isPlayer1() {
        CurrentUserDetails u = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentMatch.getPlayer1().equals(u.getUserDto());
    }

    public boolean isPlayer2() {
        CurrentUserDetails u = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentMatch.getPlayer2().equals(u.getUserDto());
    }

    public void agreeProposal(ProposalDTO p) {
        currentMatch.setPlayed(p.getPropDate());
        proposalService.agreedProposal(p);
    }

    public String showMatchDetail(MatchDTO m,List<PlaceDTO> p) {
        setCurrentMatch(m);
        setPossiblePlaces(p);
        refresh();      
        return "matchDetail";
    }
    
      /**
     * @return the currentMatch
     */
    public MatchDTO getCurrentMatch() {
        return currentMatch;
    }

    /**
     * @param currentMatch the currentMatch to set
     */
    public void setCurrentMatch(MatchDTO currentMatch) {
        this.currentMatch = currentMatch;
    }

    /**
     * @return the proposal
     */
    public ProposalDTO getProposal() {
        return proposal;
    }

    /**
     * @param proposal the proposal to set
     */
    public void setProposal(ProposalDTO proposal) {
        this.proposal = proposal;
    }

    /**
     * @return the scoreService
     */
    public ScoreService getScoreService() {
        return scoreService;
    }

    /**
     * @param scoreService the scoreService to set
     */
    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * @return the set
     */
    public SetDTO getSet() {
        return set;
    }

    /**
     * @param set the set to set
     */
    public void setSet(SetDTO set) {
        this.set = set;
    }

    /**
     * @return the possiblePlaces
     */
    public List<PlaceDTO> getPossiblePlaces() {
        return possiblePlaces;
    }

    /**
     * @param possiblePlaces the possiblePlaces to set
     */
    public void setPossiblePlaces(List<PlaceDTO> possiblePlaces) {
        this.possiblePlaces = possiblePlaces;
    }

    
}
