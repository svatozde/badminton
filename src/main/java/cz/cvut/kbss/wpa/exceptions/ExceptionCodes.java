/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.exceptions;

import java.io.Serializable;

/**
 *
 * @author zdeněk
 */
 public enum ExceptionCodes implements ExceptionCode{
    
     
     ///player service ode
    EXP00("Default message"),
    EXP01("Id canot be null"),
    EXP02("Input DTO can not be null"),
    EXP03("Player name can not be null"),
    EXP04("Player surename can not be null"),
    EXP05("Player password can not be null"),
    EXP06("Player weight must be be between 20 - 200 kg"),
    EXP07("Player height must be between 75 - 280 cm"),
    EXP08("Date of birth can not be null"),
    EXP09("Player must be older than 15 years"),
    EXP10("Players age must be between 15 - 130"),
    EXP11("Players username must not be null"),
    EXP12("Player name can not be balnk"),
    EXP14("Player surename can not be blank"),
    EXP15("Player username can not be blank"),
    EXP16("Player password can not be blank"),
    EXP17("Player username can not be null"),
    EXP18("Plauyer username can not be blanlk"),
    EXP19("Player with this id doe not exists");
    
    private ExceptionCodes(String message, String i18n_code){
        this.message = message;
        this.i18n_code = i18n_code;
    }
    
    private ExceptionCodes(String message){
        this.message = message;
        this.i18n_code = "Unknown";
    }
    
    private String message;
    private String i18n_code;

    /**
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getI18n_code() {
        return i18n_code;
    }

    @Override
    public String toString() {
        return super.toString() + " " + message; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
