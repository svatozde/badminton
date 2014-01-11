/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dao.GenericDAOIface;
import cz.cvut.kbss.wpa.dto.EnrollDTO;
import cz.cvut.kbss.wpa.dto.EnrollUserDTO;
import cz.cvut.kbss.wpa.dto.LeagueDTO;
import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.PlaceDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.ProposalDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.model.Enroll;
import cz.cvut.kbss.wpa.model.League;
import cz.cvut.kbss.wpa.model.Match;
import cz.cvut.kbss.wpa.model.Note;
import cz.cvut.kbss.wpa.model.Place;
import cz.cvut.kbss.wpa.model.Player;
import cz.cvut.kbss.wpa.model.Proposal;
import cz.cvut.kbss.wpa.model.Set;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service("leagueService")
public class LeagueServiceImpl implements LeagueService, Serializable {

    @Autowired
    private GenericDAOIface genericDAOIface;

    public List<LeagueDTO> getLeaguesNotStarted() {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("today", new Date());
        List<League> leagues = genericDAOIface.getByNamedQuery(League.Q_GET_ENROLLED_LEAGUES, m, League.class);
        return remapLegues(leagues);
    }

    public void createLeague(LeagueDTO league) {
        League l = new League();
        l.setName(league.getName());
        l.setStart(league.getLeagueStart());
        l.setEnd(league.getLeagueEnd());
        genericDAOIface.saveOrUpdate(l);

        List<Long> ids = new ArrayList<Long>();
        for (PlaceDTO p : league.getPossiblePlaces()) {
            ids.add(p.getId());
        }
        List<Place> places = genericDAOIface.getByListOfIds(ids, Place.class);
        l.setAllowedPlaces(places);

        genericDAOIface.saveOrUpdate(l);

    }

    public void startLeague(LeagueDTO league) {
        League l = genericDAOIface.getById(league.getId(), League.class);
        if (l.getMatches() != null && !l.getMatches().isEmpty()) {
            return;
        }
        List<Enroll> es = l.getEnrolls();

        List<Match> ms = new ArrayList<Match>();

        for (int i = 0; i <= es.size(); i++) {
            for (int j = i + 1; j < es.size(); j++) {
                Enroll e1 = es.get(i);
                Enroll e2 = es.get(j);
                if (e1 != e2) {
                    Match m = new Match();
                    ms.add(m);
                    m.setLeague(l);
                    genericDAOIface.saveOrUpdate(m);

                    Note n1 = new Note();
                    n1.setPlayer(e1.getPlayer());
                    n1.setMatch(m);
                    n1.setNumber(1);
                    genericDAOIface.saveOrUpdate(n1);

                    Note n2 = new Note();
                    n2.setPlayer(e2.getPlayer());
                    n2.setMatch(m);
                    n2.setNumber(2);
                    genericDAOIface.saveOrUpdate(n2);

                }
            }
        }
        l.setMatches(ms);
        l.setStart(new Date());
        genericDAOIface.saveOrUpdate(l);

    }

    public List<LeagueDTO> getAllLeagues() {
        List<League> leagues = genericDAOIface.getAll(League.class);
        return remapLegues(leagues);
    }

    private List<LeagueDTO> remapLegues(List<League> in) {
        List<LeagueDTO> ret = new ArrayList<LeagueDTO>();
        for (League l : in) {
            ret.add(remapLeague(l));
        }

        return ret;
    }

    private LeagueDTO remapLeague(League l) {
        LeagueDTO ldto = new LeagueDTO();

        ldto.setStarted(!l.getMatches().isEmpty());
        
        ldto.setName(l.getName());
        ldto.setId(l.getId());

        List<PlaceDTO> places = new ArrayList<PlaceDTO>();
        ldto.setPossiblePlaces(places);
        
        for (Place p : l.getAllowedPlaces()) {
            places.add(mapPlace(p));
        }
        java.util.Set<PlayerDTO> ps = new HashSet<PlayerDTO>();
        for (Match m : l.getMatches()) {
            for (Note n : m.getNotes()) {
                Player p = n.getPlayer();
                ps.add(remapPlayer(p));
            }     
        }
        
        
        List<EnrollDTO> es = new ArrayList<EnrollDTO>();
        for(Enroll e : l.getEnrolls()){
            PlayerDTO p = remapPlayer(e.getPlayer());
            EnrollDTO en = new EnrollDTO();
            en.setId(e.getId());
            en.setPlayer(p);
            en.setLeague(ldto);
            es.add(en);
        }
        ldto.setEnrolls(es);
        
       
        
        return ldto;

    }
    
    

    private PlaceDTO mapPlace(Place p) {
        PlaceDTO ret = new PlaceDTO();
        ret.setId(p.getId());
        ret.setAdress(p.getAdress());
        ret.setName(p.getName());
        return ret;
    }

    public void enrollUser(EnrollUserDTO league) {
        Long id = league.getLeagueId();
        Long pid = league.getUserId();
        League l = genericDAOIface.getById(id, League.class);
        Player p = genericDAOIface.getById(pid, Player.class);
        Enroll e = new Enroll();
        e.setLeague(l);
        e.setPlayer(p);
        genericDAOIface.saveOrUpdate(e);

    }

    public List<LeagueDTO> getEnroledLeagues(PlayerDTO dto) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("player", dto.getId());
        List<League> leagues = genericDAOIface.getByNamedQuery(League.Q_GET_ENROLLED_LEAGUES, m, League.class);
        return remapLegues(leagues);
    }

    private MatchDTO remapMatch(Match m) {
        MatchDTO ret = new MatchDTO();
        ret.setId(m.getId());
        List<Note> ns = m.getNotes();
        if (ns.size() != 2)
        {
            throw new RuntimeException("Match " + m.getId() + "does not have 2 players");
        }
        ret.setPlayer1(remapPlayer(ns.get(0).getPlayer()));
        ret.setPlayer2(remapPlayer(ns.get(1).getPlayer()));

        List<Set> s1 = ns.get(0).getSets();
        List<Set> s2 = ns.get(1).getSets();

        Collections.sort(s2, new SetComparator());
        Collections.sort(s1, new SetComparator());

        List<SetDTO> ss = new ArrayList<SetDTO>();
        for (int i = 0; i < s1.size(); i++) {
            SetDTO sDto = new SetDTO();
            sDto.setNumber(s1.get(i).getNumber());
            sDto.setScore1(s1.get(i).getScore());
            sDto.setScore2(s2.get(i).getScore());
            ss.add(sDto);
        }
        ret.setSets(ss);

        List<ProposalDTO> pr1 = new ArrayList<ProposalDTO>();
        List<ProposalDTO> pr2 = new ArrayList<ProposalDTO>();
        ret.setProposals2(pr2);
        ret.setProposals1(pr1);
        for (Proposal p : m.getProposals()) {
            if (p.getPlayer().getId() == ns.get(0).getPlayer().getId()) {
                pr1.add(remapProposal(p));

            } else {
                pr2.add(remapProposal(p));
            }
            if (p.isMatchAgreed()) {
                ret.setPlayed(p.getPropDate());
            }
        }

        return ret;

    }

    private ProposalDTO remapProposal(Proposal p) {
        ProposalDTO ret = new ProposalDTO();
        ret.setId(p.getId());
        ret.setPropDate(p.getPropDate());
        ret.setPlace(mapPlace(p.getPlace()));
        ret.setPlayer(remapPlayer(p.getPlayer()));
        ret.setAgreed(p.isMatchAgreed());
        return ret;
    }

    private PlayerDTO remapPlayer(Player p) {
        PlayerDTO ret = new PlayerDTO();
        ret.setId(p.getId());
        ret.setUsername(p.getUsername());
        ret.setDateOfBirth(p.getDateOfBirth());
        ret.setName(p.getName());
        ret.setSurname(p.getSurname());
        ret.setHeight(p.getHeight());
        ret.setWeigth(p.getWeigth());
        return ret;
    }

    public LeagueDTO getMatches(LeagueDTO league) {
        League l = genericDAOIface.getById(league.getId(), League.class);

        Map<PlayerPair, MatchDTO> ms = new HashMap<PlayerPair, MatchDTO>();
        java.util.Set<PlayerDTO> ps = new HashSet<PlayerDTO>();
        for (Match m : l.getMatches()) {
            for (Note n : m.getNotes()) {
                Player p = n.getPlayer();
                ps.add(remapPlayer(p));
            }
            MatchDTO ma = remapMatch(m);
            ms.put(new PlayerPair(ma.getPlayer2(), ma.getPlayer1()), ma);
        }
        List<PlayerDTO> sorted = new ArrayList<PlayerDTO>(ps);
        Collections.sort(sorted, new PlayerComparator());
        int count = sorted.size();
        MatchDTO[][] matches = new MatchDTO[count][count];

        for (int i = 0; i < count; i++) {
            for (int j = i; j < count; j++) {
                if (i == j) {
                    matches[i][j] = null;
                } else {
                    MatchDTO tm = ms.get(new PlayerPair(sorted.get(i), sorted.get(j)));
                    if (tm == null) {
                        tm = ms.get(new PlayerPair(sorted.get(j), sorted.get(i)));
                    }
                    matches[i][j] = tm;
                    matches[j][i] = tm;
                }
            }
        }

        LeagueDTO ret = remapLeague(l);
        ret.setPlayers(sorted.toArray(new PlayerDTO[count]));
        ret.setMatches(matches);
        return ret;
    }

    ////// helper classes
    private class PlayerComparator implements Comparator<PlayerDTO> {

        public int compare(PlayerDTO o1, PlayerDTO o2) {
            return o2.getUsername().compareTo(o1.getUsername());
        }
    }

    private class SetComparator implements Comparator<Set> {

        public int compare(Set o1, Set o2) {
            return o1.getNumber() - o2.getNumber();
        }
    }

    private class PlayerPair {

        PlayerDTO p1;
        PlayerDTO p2;

        public PlayerPair(PlayerDTO p1, PlayerDTO p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof PlayerPair)) {
                return false;
            } else {
                PlayerPair o = (PlayerPair) obj;
                return (this.p1.getId().equals(o.p1.getId()) && this.p2.getId().equals(o.p2.getId()));
            }

        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + (this.p1 != null ? this.p1.hashCode() : 0);
            hash = 71 * hash + (this.p2 != null ? this.p2.hashCode() : 0);
            return hash;
        }
    }
}
