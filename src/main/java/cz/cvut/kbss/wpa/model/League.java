/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@NamedQueries({
//	@NamedQuery(
//	name = "findEnrolledLeagues",
//	query = " from League as l where l.ID in (select e.ID from Enroll as e where e.PLAYER_ID = :player) "
//	)
//})
@Entity
@NamedQueries({
       @NamedQuery(name = League.Q_GET_ENROLLED_LEAGUES, query = "SELECT l FROM League l JOIN l.enrolls e WHERE e.player.id = :player"),
       @NamedQuery(name = League.Q_GET_ENROLED_PLAYERS, query="SELECT p FROM Player p JOIN p.enrolls e WHERE e.league.id = :league" )})
public class League extends AbstractEntity {

    public static final String Q_GET_ENROLLED_LEAGUES = "Q_GET_ENROLLED_LEAGUES";
    public static final String Q_GET_ENROLED_PLAYERS = "Q_GET_ENROLED_PLAYERS";
    
    @OneToMany(mappedBy = "league")
    private List<Match> matches;

    @ManyToMany
    @JoinTable(name = "posible_places")
    private List<Place> allowedPlaces;

    @OneToMany(mappedBy = "league")
    private List<Enroll> enrolls;

    protected String name;

    @Temporal(TemporalType.DATE)
    protected Date leagueStart;

    @Temporal(TemporalType.DATE)
    protected Date leagueEnd;
    

    /**
     * @return the matches
     */
    public List<Match> getMatches() {
        return matches;
    }

    /**
     * @param matches the matches to set
     */
    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /**
     * @return the enrolls
     */
    public List<Enroll> getEnrolls() {
        return enrolls;
    }

    /**
     * @param enrolls the enrolls to set
     */
    public void setEnrolls(List<Enroll> enrolls) {
        this.enrolls = enrolls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return leagueStart;
    }

    public void setStart(Date start) {
        this.leagueStart = start;
    }

    public Date getEnd() {
        return leagueEnd;
    }

    public void setEnd(Date end) {
        this.leagueEnd = end;
    }

    /**
     * @return the allowedPlaces
     */
    public List<Place> getAllowedPlaces() {
        return allowedPlaces;
    }

    /**
     * @param allowedPlaces the allowedPlaces to set
     */
    public void setAllowedPlaces(List<Place> allowedPlaces) {
        this.allowedPlaces = allowedPlaces;
    }

}
