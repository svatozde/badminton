/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.Date;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *
 * @author zdeněk
 */
public class PlayerDTO extends UserDTO {
    
    protected String name;
    protected String surname;
    protected Integer weigth;
    protected Integer Height;
    protected Date dateOfBirth;
    protected List<NoteDTO> notes;
    protected List<EnrollDTO> enrolls;

     public PlayerDTO(){
         super(null, null, null);
     }
    
    public PlayerDTO(Long id, String username, String password,
            String name, String surname, Integer weigth, Integer height, Date dateOfBirth, List<NoteDTO> notes, List<EnrollDTO> enrolls) {
        super(id, username, password);
        this.name = name;
        this.surname = surname;
        this.weigth = weigth;
        this.Height = height;
        this.dateOfBirth = dateOfBirth;
        this.notes = notes;
        this.enrolls = enrolls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWeigth() {
        return weigth;
    }

    public void setWeigth(Integer weigth) {
        this.weigth = weigth;
    }

    public Integer getHeight() {
        return Height;
    }

    public void setHeight(Integer Height) {
        this.Height = Height;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<NoteDTO> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteDTO> notes) {
        this.notes = notes;
    }

    public List<EnrollDTO> getEnrolls() {
        return enrolls;
    }

    public void setEnrolls(List<EnrollDTO> enrolls) {
        this.enrolls = enrolls;
    }
    
     @Override
    public GrantedAuthority getGrantedAuthority() {
        return new GrantedAuthorityImpl("ROLE_PLAYER");
    }
    
}
