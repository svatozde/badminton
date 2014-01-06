/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.model;

import cz.cvut.kbss.wpa.provider.HashProvider;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author zdeněk
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorOptions(force=true)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type", length = 20)
@DiscriminatorValue(value = "user")
@Configurable(preConstruction=true)
public class User extends AbstractEntity {

    @Column(length = 40, nullable = false,unique = true)
    private String username;

    @Column(length = 40, nullable = false)
    private String password;

    @Autowired
    private transient HashProvider hashProvider;

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
        this.password = this.hashProvider.computeHash(password);
    }

    /**
     * @return the provider
     */
    public HashProvider getProvider() {
        return hashProvider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(HashProvider provider) {
        this.hashProvider = provider;
    }

}
