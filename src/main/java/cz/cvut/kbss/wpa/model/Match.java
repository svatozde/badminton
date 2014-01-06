/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zdeněk
 */
@Entity
@Table(name = "matches")
public class Match extends AbstractEntity {
    
    
    @OneToMany(mappedBy="match")
    private List<Note> notes;
    
    @ManyToOne
    private League league;
    
    @OneToMany(mappedBy="match")
    private List<Proposal> proposals;
    
    /**
     * @return the notes
     */
    public List<Note> getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    /**
     * @return the league
     */
    public League getLeague() {
        return league;
    }

    /**
     * @param league the league to set
     */
    public void setLeague(League league) {
        this.league = league;
    }

    /**
     * @return the proposals
     */
    public List<Proposal> getProposals() {
        return proposals;
    }

    /**
     * @param proposals the proposals to set
     */
    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }
    


    
    
    
}
