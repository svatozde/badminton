/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jan
 */
public class PlaceDTO extends AbstractDTO{
    
    protected List<LeagueDTO> leagues = new ArrayList<LeagueDTO>();
    protected List<ProposalDTO> proposals = new ArrayList<ProposalDTO>();
    protected String name;
    protected String adress;

    public PlaceDTO(Long id, List<LeagueDTO> leagues, List<ProposalDTO> proposals, String name, String adress) {
        super(id);
        this.leagues = leagues;
        this.proposals = proposals;
        this.name = name;
        this.adress = adress;
    }

    public PlaceDTO() {    
    }

    public List<LeagueDTO> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<LeagueDTO> leagues) {
        this.leagues = leagues;
    }

    public List<ProposalDTO> getProposals() {
        return proposals;
    }

    public void setProposals(List<ProposalDTO> proposals) {
        this.proposals = proposals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}
