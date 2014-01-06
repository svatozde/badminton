/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author zdeněk
 */
@Entity
@DiscriminatorValue("player")
public class Player extends User {

    private String name;
    private String surname;

    private Integer weigth;
    private Integer height;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Note> notes;

    @OneToMany(mappedBy = "player")
    private List<Enroll> enrolls;

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

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the weigth
     */
    public Integer getWeigth() {
        return weigth;
    }

    /**
     * @param weigth the weigth to set
     */
    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

}
