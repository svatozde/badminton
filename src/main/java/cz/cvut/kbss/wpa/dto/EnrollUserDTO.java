/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

import java.util.Date;

/**
 *
 * @author zdeněk
 */
public class EnrollUserDTO {
    
    private Long userId;
    
    private Long leagueId;
    
    private Date paid;

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the leagueId
     */
    public Long getLeagueId() {
        return leagueId;
    }

    /**
     * @param leagueId the leagueId to set
     */
    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    /**
     * @return the paid
     */
    public Date getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(Date paid) {
        this.paid = paid;
    }
    
}
