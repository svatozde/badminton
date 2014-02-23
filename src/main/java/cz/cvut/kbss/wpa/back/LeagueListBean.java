package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.EnrollUserDTO;
import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.ProposalDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.security.CurrentUserDetails;
import cz.cvut.kbss.wpa.service.api.LeagueService;
import cz.cvut.kbss.wpa.service.api.ProposalService;
import cz.cvut.kbss.wpa.service.api.ScoreService;
import cz.cvut.kbss.wpa.support.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
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
@RequestScoped
public class LeagueListBean implements Serializable {

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private ScoreService scoreService;

    private LeagueDTO currentLeagueMatches;

   

    public List<LeagueDTO> getAllLeagues() {
        List<LeagueDTO> all = leagueService.getAllLeagues();
        Object u = FacesUtil.getCurrentUserDTO();
        if (u instanceof PlayerDTO) {
            List<LeagueDTO> ens = leagueService.getEnroledLeagues((PlayerDTO) u);
            all.removeAll(ens);
        }
        
        return all;
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
        dto.setStarted(true);
        leagueService.startLeague(dto);
    }

    /**
     * @return the enroledLeagues
     */
    public List<LeagueDTO> getEnroledLeagues() {
        CurrentUserDetails u = (CurrentUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (u.getUserDto() instanceof PlayerDTO) {
            return leagueService.getEnroledLeagues((PlayerDTO) u.getUserDto());
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
  
}
