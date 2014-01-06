/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.EnrollUserDTO;
import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.ProposalDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.security.CurrentUserDetails;
import cz.cvut.kbss.wpa.service.LeagueService;
import cz.cvut.kbss.wpa.service.ProposalService;
import cz.cvut.kbss.wpa.service.ScoreService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author zdeněk
 */
@Component("leagueList")
@Scope("session")
public class LeagueListBean implements Serializable {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ScoreService scoreService;

    private LeagueDTO currentLeagueMatches;

    private MatchDTO currentMatch;

    private ProposalDTO proposal;

    private SetDTO set;

    public  boolean isEdit() {
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
        set.setNumber(currentMatch.getSets().size()+1);

    }

    public void update(SetDTO dto) {
        scoreService.updateSet(currentMatch, dto);
        for (SetDTO ss : currentMatch.getSets()) {
            ss.setEditable(false);
        }
    }

    public void setEditable(SetDTO s) {
        s.setEditable(true);
    }

    public void saveScore() {
        for (SetDTO s : currentMatch.getSets()) {
            s.setEditable(false);
        }
    }

    public void listener1(AjaxBehaviorEvent e) {
        System.out.println("Made it!");
    }

    public void listener2(AjaxBehaviorEvent e) {
        System.out.println("Made it!");
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

    private void refresh() {
        proposal = new ProposalDTO();
        proposal.setMatch(currentMatch);
        CurrentUserDetails u = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        proposal.setPlayer((PlayerDTO) u.getUserDto());
        set = new SetDTO();
        set.setNumber(currentMatch.getSets().size()+1);
        for (SetDTO s : currentMatch.getSets()) {
            s.setEditable(false);
        }
    }

    public List<SelectItem> getAllPlaces() {
        List<SelectItem> places = new ArrayList<SelectItem>();
        for (PlaceDTO c : currentLeagueMatches.getPossiblePlaces()) {
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

    public String showMatchDetail(MatchDTO m) {
        setCurrentMatch(m);
        refresh();
        proposal.setMatch(m);
        return "matchDetail";
    }

    public List<LeagueDTO> getAllLeagues() {
        return leagueService.getAllLeagues();
    }

    public List<LeagueDTO> getEnrolledLeagues() {
        Object u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (u instanceof PlayerDTO) {
            return leagueService.getEnroledLeagues((PlayerDTO) u);
        }

        return new ArrayList<LeagueDTO>();
    }

    public String leagueDetail(LeagueDTO dto) {
        currentLeagueMatches = leagueService.getMatches(dto);
        return "leagueDetail";
    }

    public void enroll(LeagueDTO dto) {
        Object u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (u instanceof CurrentUserDetails) {
            EnrollUserDTO e = new EnrollUserDTO();
            e.setLeagueId(dto.getId());
            e.setUserId(((CurrentUserDetails) u).getUserDto().getId());
            leagueService.enrollUser(e);
        }
    }

    public void startLeague(LeagueDTO dto) {
        leagueService.startLeague(dto);
    }

    /**
     * @return the enroledLeagues
     */
    public List<LeagueDTO> getEnroledLeagues() {
        Object u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (u instanceof CurrentUserDetails) {
            PlayerDTO dto = (PlayerDTO) ((CurrentUserDetails) u).getUserDto();
            return leagueService.getEnroledLeagues(dto);
        }
        return new ArrayList<LeagueDTO>();
    }

    /**
     * @return the currentLeagueMatches
     */
    public LeagueDTO getCurrentLeagueMatches() {
        return currentLeagueMatches;
    }

    /**
     * @param currentLeagueMatches the currentLeagueMatches to set
     */
    public void setCurrentLeagueMatches(LeagueDTO currentLeagueMatches) {
        this.currentLeagueMatches = currentLeagueMatches;
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

}
