package cz.cvut.kbss.wpa.dao;

import cz.cvut.kbss.wpa.dto.MatchDTO;
import cz.cvut.kbss.wpa.model.AbstractEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 * Implementuje (hibernatem) akce GenericDAOIface zpusobem obvyklym pro prumerne
 * dao.
 *
 * Tato implementace je postavena nad JPA + rozsirenimi frameworku Hibernate
 *
 * @author Pavel Micka (mickapa1@fel.cvut.cz)
 */
@Component("genericDAO")
public class GenericHibernateJpaDAO implements GenericDAOIface, Serializable {

    @Autowired
    private EntityManagerFactory entityManagerfactory;

    /**
     * Get entity manager for the current transaction
     *
     * @return
     */
    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(getEntityManagerfactory());
    }

    /**
     * Vrati vsechny objekty dane tridy
     *
     * @return vsechny objekty tridy, jez je injektovana jako clazz, serazene
     * dle id sestupne
     */
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e").getResultList();
    }

    /**
     * Vrati vsechny objekty serazene sestupne dle dane property
     *
     * @param property
     * @return
     */
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Vrati vsechny objekty serazene vzestupne dle dane property
     *
     * @param property
     * @return
     */
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * Vrati objekty dane tridy, jejichz property se rovna objektu predanemu v
     * parametru, serazene dle id sestupne
     *
     * @param property property, kterou porovnavame
     * @param value hodnota, se kterou porovnavame
     * @return vsechny vyhovujici zaznamy
     */
    @SuppressWarnings("unchecked")
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> clazz) {
        String queryString = "SELECT e FROM " + clazz.getSimpleName() + " e WHERE e." + property + " = :value";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }

    /**
     * Smaze objekt dle daneho ID
     *
     * @param id id objektu je smazani
     */
    public <ENTITY extends AbstractEntity> void removeById(long id, Class<ENTITY> clazz) {
        ENTITY e = getEntityManager().find(clazz, id);
        if (e != null) {
            getEntityManager().remove(e);
        }
    }

    /**
     * smaze danou entitu
     *
     * @param o entita ke smazani
     */
    public <ENTITY extends AbstractEntity> void remove(ENTITY o) {
        getEntityManager().remove(o);
    }

    /**
     * Vrati objekt (pomoci get) dane tridy dle ID
     *
     * @param id id objektu k vraceni
     * @return objekt identifikovany id, @null pokud neexistuje
     */
    @SuppressWarnings("unchecked")
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> clazz) {
        return getEntityManager().find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> ENTITY loadById(long id, Class<ENTITY> clazz) {
        return (ENTITY) ((Session) getEntityManager().getDelegate()).load(clazz, id);
    }

    public <ENTITY extends AbstractEntity> ENTITY saveOrUpdate(ENTITY o) {
        if (o.getId() == null) {
            getEntityManager().persist(o);
        } else {
            getEntityManager().merge(o);
        }
        return o;
    }

    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> clazz) {
        ENTITY e;
        if (value == null) {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " IS NULL").getSingleResult());
        } else {
            e = clazz.cast(getEntityManager().createQuery("FROM " + clazz.getSimpleName() + " WHERE " + property + " = :value").setParameter("value", value).getSingleResult());
        }
        return e;
    }

    public <ENTITY> List<ENTITY> getByListOfIds(List<Long> ids, Class<ENTITY> clazz) {
        return getEntityManager().createQuery("SELECT e FROM " + clazz.getSimpleName() + " e WHERE e.id IN (:values)").setParameter("values", ids).getResultList();
    }

    public Long getCount(Class clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    public <ENTITY> List<ENTITY> getPage(int from, int maxResults, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> clazz) {
        throw new IllegalStateException("Not implemented yet");
    }

    public void merge(Object o) {
        throw new IllegalStateException("Not implemented yet");
    }

    /**
     * @return the entityManagerfactory
     */
    public EntityManagerFactory getEntityManagerfactory() {
        return entityManagerfactory;
    }

    /**
     * @param entityManagerfactory the entityManagerfactory to set
     */
    public void setEntityManagerfactory(EntityManagerFactory entityManagerfactory) {
        this.entityManagerfactory = entityManagerfactory;
    }

    public <ENTITY> List<ENTITY> getByNamedQuery(String queryName, Map<String, Object> values, Class<ENTITY> clazz) {
        javax.persistence.Query q = getEntityManager().createNamedQuery(queryName);
        for (Entry<String, Object> e : values.entrySet()) {
            q.setParameter(e.getKey(), e.getValue());
        }
        return q.getResultList();
    }


}
