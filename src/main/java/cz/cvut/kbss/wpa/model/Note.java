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

/**
 *
 * @author zdeněk
 */
@Entity
public class Note extends AbstractEntity{
    
    @ManyToOne
    private Player player;
    
    @ManyToOne
    private Match match;
    
    @OneToMany(mappedBy = "note")
    private List<Set> sets;
    
    protected int number;
    
    protected int score;

    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return the match
     */
    public Match getMatch() {
        return match;
    }

    /**
     * @param match the match to set
     */
    public void setMatch(Match match) {
        this.match = match;
    }

    /**
     * @return the sets
     */
    public List<Set> getSets() {
        return sets;
    }

    /**
     * @param sets the sets to set
     */
    public void setSets(List<Set> sets) {
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
