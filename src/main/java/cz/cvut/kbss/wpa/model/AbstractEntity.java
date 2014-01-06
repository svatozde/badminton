/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractEntity is superobject for all entity beans.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    /**
     * Pro ciselny klic pouzit automaticke generovani napr. sekvencemi tzn.
     * misto Auto, kdy se ponecha volba na databazi, zvolit GenerationType.SEQUENCE
     * a nasledne jeste definovat jmeno sekvence, jinak bude pouzita vychozi
     * - spolecna pro vsechny entity
     * @GeneratedValue(strategy=GenerationType.SEQUENCE)
     * @SequenceGenerator(sequenceName="seq_department")
     *
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
    
    public AbstractEntity() {
    }
    
    public AbstractEntity(Long id) {
        this.id = id;
    }
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof AbstractEntity)) {
            return false;
        }
        return ((AbstractEntity) obj).getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
