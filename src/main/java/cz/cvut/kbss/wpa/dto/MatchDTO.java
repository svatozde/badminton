/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author zdeněk
 */
public class MatchDTO extends AbstractDTO{
    
    private PlayerDTO player1;
    private PlayerDTO player2;
    private List<ProposalDTO> proposals1;
    private List<ProposalDTO> proposals2;
    private List<SetDTO> sets;
    private Date played;

    
    public MatchDTO() {
      
    }

   
    /**
     * @return the player1
     */
    public PlayerDTO getPlayer1() {
        return player1;
    }

    /**
     * @param player1 the player1 to set
     */
    public void setPlayer1(PlayerDTO player1) {
        this.player1 = player1;
    }

    /**
     * @return the player2
     */
    public PlayerDTO getPlayer2() {
        return player2;
    }

    /**
     * @param player2 the player2 to set
     */
    public void setPlayer2(PlayerDTO player2) {
        this.player2 = player2;
    }

    /**
     * @return the sets
     */
    public List<SetDTO> getSets() {
        return sets;
    }

    /**
     * @param sets the sets to set
     */
    public void setSets(List<SetDTO> sets) {
        this.sets = sets;
    }
    
    
    public String getScore(){
        int p1s = 0;
        int p2s = 0;
        for(SetDTO s : sets){
            if(s.getScore1() > s.getScore2()) p1s++;
            else p2s++;
        }
        
        return " " + p1s + ":" + p2s + " ";
    }

    /**
     * @return the played
     */
    public Date getPlayed() {
        return played;
    }

    /**
     * @param played the played to set
     */
    public void setPlayed(Date played) {
        this.played = played;
    }

    /**
     * @return the proposals2
     */
    public List<ProposalDTO> getProposals2() {
        return proposals2;
    }

    /**
     * @param proposals2 the proposals2 to set
     */
    public void setProposals2(List<ProposalDTO> proposals2) {
        this.proposals2 = proposals2;
    }

    /**
     * @return the proposals1
     */
    public List<ProposalDTO> getProposals1() {
        return proposals1;
    }

    /**
     * @param proposals1 the proposals1 to set
     */
    public void setProposals1(List<ProposalDTO> proposals1) {
        this.proposals1 = proposals1;
    }
    
    
}
