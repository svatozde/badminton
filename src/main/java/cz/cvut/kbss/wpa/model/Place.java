/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author zdeněk
 */
@Entity
public class Place extends AbstractEntity {

    @OneToMany(mappedBy = "place")
    private List<Proposal> proposals;

    @Column(length = 40, nullable = false, unique = true)
    private String name;

    @Column(length = 40, nullable = false, unique = false)
    protected String adress;

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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
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
