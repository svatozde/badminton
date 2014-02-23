package cz.cvut.kbss.wpa.dto;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;

public class AbstractDTO implements Serializable, Cloneable {

    protected Long id;

    public AbstractDTO() {
    }

    public AbstractDTO(Long id) {
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
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractDTO other = (AbstractDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object o = null;
        try {
            o = getClass().newInstance();
            BeanUtils.copyProperties(this, o);
        } catch (InstantiationException ex) {
            Logger.getLogger(AbstractDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(AbstractDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;

    }

}
