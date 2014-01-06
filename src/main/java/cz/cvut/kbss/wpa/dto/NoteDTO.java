/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.List;

/**
 *
 * @author jan
 */
public class NoteDTO extends AbstractDTO{
    
    protected PlayerDTO player;
    protected MatchDTO match;
    protected List<SetDTO> sets;
    protected int number;
    protected int score;

    public NoteDTO(Long id, PlayerDTO player, MatchDTO match, List<SetDTO> sets, int number, int score) {
        super(id);
        this.player = player;
        this.match = match;
        this.sets = sets;
        this.number = number;
        this.score = score;
    }
    
    

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public MatchDTO getMatch() {
        return match;
    }

    public void setMatch(MatchDTO match) {
        this.match = match;
    }

    public List<SetDTO> getSets() {
        return sets;
    }

    public void setSets(List<SetDTO> sets) {
        this.sets = sets;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
