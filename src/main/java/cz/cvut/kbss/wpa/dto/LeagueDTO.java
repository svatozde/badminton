/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zdeněk
 */
public class LeagueDTO extends AbstractDTO {

    private PlayerDTO[] players;

    private MatchDTO[][] matches;

    private List<EnrollDTO> enrolls = new ArrayList<EnrollDTO>();
    private List<PlaceDTO> possiblePlaces = new ArrayList<PlaceDTO>();
    private String name;
    private Date leagueStart;
    private Date leagueEnd;

    public LeagueDTO() {

    }

    public LeagueDTO(Long id, MatchDTO[][] matches, List<EnrollDTO> enrolls, String name, Date leagueStart, Date leagueEnd) {
        super(id);
        this.enrolls = enrolls;
        this.matches = matches;
        this.name = name;
        this.leagueStart = leagueStart;
        this.leagueEnd = leagueEnd;

    }

    public List<EnrollDTO> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(List<EnrollDTO> enrolls) {
        this.enrolls = enrolls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLeagueStart() {
        return leagueStart;
    }

    public void setLeagueStart(Date leagueStart) {
        this.leagueStart = leagueStart;
    }

    public Date getLeagueEnd() {
        return leagueEnd;
    }

    public void setLeagueEnd(Date leagueEnd) {
        this.leagueEnd = leagueEnd;
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

    /**
     * @return the players
     */
    public PlayerDTO[] getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(PlayerDTO[] players) {
        this.players = players;
    }

    /**
     * @param matches the matches to set
     */
    public void setMatches(MatchDTO[][] matches) {
        this.matches = matches;
    }

    /**
     * @return the matches
     */
    public MatchDTO[][] getMatches() {
        return matches;
    }

}
