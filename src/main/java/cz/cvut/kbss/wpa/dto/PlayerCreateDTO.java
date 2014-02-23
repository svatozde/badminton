/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import cz.cvut.kbss.wpa.exceptions.PlayerServiceException;
import cz.cvut.kbss.wpa.service.aspects.anotations.DateOfBirth;
import cz.cvut.kbss.wpa.service.aspects.anotations.Interval;
import cz.cvut.kbss.wpa.service.aspects.anotations.NotBlank;
import cz.cvut.kbss.wpa.service.aspects.anotations.NotNull;
import cz.cvut.kbss.wpa.service.aspects.anotations.Validate;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zdeněk
 */
@Validate(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP02)
public class PlayerCreateDTO implements Serializable,Cloneable{
    @NotNull(code = ExceptionCodes.EXP17)
    @NotBlank(code = ExceptionCodes.EXP18)
    private String username;
    
    @NotNull(code = ExceptionCodes.EXP05)
    @NotBlank(code = ExceptionCodes.EXP16)
    private String password;
    
    @NotNull(code = ExceptionCodes.EXP03)
    @NotBlank(code = ExceptionCodes.EXP12)
    protected String name;
    
    @NotNull(code = ExceptionCodes.EXP04)
    @NotBlank(code=ExceptionCodes.EXP14)
    protected String surname;
    
    @Interval(code =ExceptionCodes.EXP06,high = 200,low=20 )
    protected Integer weigth;
    
    @Interval(code= ExceptionCodes.EXP07,high= 280, low=75)
    protected Integer Height;
    
    @NotNull(code = ExceptionCodes.EXP08)
    @DateOfBirth(code = ExceptionCodes.EXP09,years = 15)
    protected Date dateOfBirth;

     public PlayerCreateDTO(){

     }
    
    public PlayerCreateDTO(Long id, String username, String password,
            String name, String surname, Integer weigth, Integer height, Date dateOfBirth) {

        this.name = name;
        this.surname = surname;
        this.weigth = weigth;
        this.Height = height;
        this.dateOfBirth = dateOfBirth;
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
