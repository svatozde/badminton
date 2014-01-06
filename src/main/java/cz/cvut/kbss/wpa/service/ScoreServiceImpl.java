/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dao.GenericHibernateJpaDAO;
import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.model.Match;
import cz.cvut.kbss.wpa.model.Note;
import cz.cvut.kbss.wpa.model.Set;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zdeněk
 */
@Service("scoreService")
public class ScoreServiceImpl implements ScoreService, Serializable {

    @Autowired
    private GenericHibernateJpaDAO genericHybernateJpaDAO;

    public void addScore(MatchDTO m) {
        Match ma = genericHybernateJpaDAO.getById(m.getId(), Match.class);
        List<Note> n = ma.getNotes();
        List<Set> s0 = new ArrayList<Set>();
        List<Set> s1 = new ArrayList<Set>();
        n.get(0).setSets(s0);
        n.get(1).setSets(s1);
        int i = 1;
        for (SetDTO s : m.getSets()) {
            Set ss0 = new Set();
            ss0.setNote(n.get(0));
            s0.add(ss0);
            ss0.setNumber(i);
            ss0.setScore(s.getScore1());
            

            Set ss1 = new Set();
            ss1.setNote(n.get(0));
            s1.add(ss1);
            ss1.setNumber(i);
            ss1.setScore(s.getScore1());
            i++;
        }
         genericHybernateJpaDAO.saveOrUpdate(ma);

       
    }

    public void removeScore(MatchDTO m) {
         Match ma = genericHybernateJpaDAO.getById(m.getId(), Match.class);
        for(Note  n  : ma.getNotes()){
            for(Set s : n.getSets()){
                genericHybernateJpaDAO.remove(s);
            }
        }
    }
    
    public void addSet(MatchDTO m, SetDTO s) {
        Match ma = genericHybernateJpaDAO.getById(m.getId(), Match.class);
        List<Note> ns =   ma.getNotes();
        Note n1 = null;
        Note n2 = null;
        
        for(Note n: ns){
            if(n.getNumber() == 1){
                n1 = n;
            }
            
            if(n.getNumber()== 2){
                n2 = n;
            }
        }
        
        List<Set> ss1 = n1.getSets();
        List<Set> ss2 = n2.getSets();
        
        s.setNumber(ss1.size()+1);
        
        Set s1 = new Set();
        s1.setNote(ns.get(0));
        s1.setNumber(s.getNumber());
        s1.setScore(s.getScore1());
        ss1.add(s1);
        
        genericHybernateJpaDAO.saveOrUpdate(s1);
        
        
        Set s2 = new Set();
        s2.setNote(ns.get(1));
        s2.setNumber(s.getNumber());
        s2.setScore(s.getScore2());
        ss2.add(s2);
        
        
        genericHybernateJpaDAO.saveOrUpdate(s2); 
        
        m.getSets().add(s);
    }

    public void updateSet(MatchDTO m, SetDTO s) {
        Match ma = genericHybernateJpaDAO.getById(m.getId(), Match.class);
        List<Note> ns =   ma.getNotes();
        Note n1 = null;
        Note n2 = null;
        
        for(Note n: ns){
            if(n.getNumber() == 1){
                n1 = n;
            }
            
            if(n.getNumber()== 2){
                n2 = n;
            }
        }
        
        for(Set set : n1.getSets()){
            if(set.getNumber() == s.getNumber() ){
                set.setScore(s.getScore1());
                genericHybernateJpaDAO.saveOrUpdate(set);
            }
        }
        
        
         for(Set set : n2.getSets()){
            if(set.getNumber() == s.getNumber() ){
                set.setScore(s.getScore2());
                genericHybernateJpaDAO.saveOrUpdate(set);
            }
        }
       
    }

    /**
     * @return the genericHybernateJpaDAO
     */
    public GenericHibernateJpaDAO getGenericHybernateJpaDAO() {
        return genericHybernateJpaDAO;
    }

    /**
     * @param genericHybernateJpaDAO the genericHybernateJpaDAO to set
     */
    public void setGenericHybernateJpaDAO(GenericHibernateJpaDAO genericHybernateJpaDAO) {
        this.genericHybernateJpaDAO = genericHybernateJpaDAO;
    }

    

}
