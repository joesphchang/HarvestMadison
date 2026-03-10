package com.joeychang.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.joeychang.utilities.PropertiesLoader;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * The type Generic dao.
 *
 * @param <T> the type parameter
 */
public class GenericDao<T> implements PropertiesLoader {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    // Properties
    //    private Properties properties;

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the type
     */
    public GenericDao(Class<T> type) {
        this.type = type;

        try {
            Properties properties = loadProperties("/database.properties");
            logger.info("Successfully loaded database properties for {}", type.getSimpleName());
        } catch (Exception exception) {
            logger.error("Failed to load database properties within GenericDao", exception);
        }
    }

    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();

    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;

    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public T getById(int id) {
        Session session = getSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Delete.
     *
     * @param entity the entity
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Insert int.
     *
     * @param entity the entity
     * @return the int
     */
    public int insert(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);
        transaction.commit();
        int id = (int) session.getIdentifier(entity);

        session.close();
        return id;
    }

    /**
     * Update.
     *
     * @param entity the entity
     */
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }


    /**
     * Find by property equal list.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the list
     */
    public List<T> findByPropertyEqual(String propertyName, Object value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.equal(propertyPath, value));
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Find by property like list.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the list
     */
    public List<T> findByPropertyLike(String propertyName, String value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }
}