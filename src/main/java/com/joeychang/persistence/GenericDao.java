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
 * Generic DAO that handles common database operations for any entity type.
 * Uses the Hibernate Criteria API so we're not writing raw SQL everywhere.
 *
 * @param <T> the entity type this DAO operates on
 */
public class GenericDao<T> implements PropertiesLoader {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity class this DAO will work with
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
        int id = (int) session.getIdentifier(entity);
        transaction.commit();

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

    /**
     * Gets by id with collection.
     *
     * @param id             the id
     * @param collectionName the collection name
     * @return the by id with collection
     */
    public T getByIdWithCollection(int id, String collectionName) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        root.fetch(collectionName);
        query.where(builder.equal(root.get("id"), id));
        T entity = session.createQuery(query).uniqueResult();
        session.close();
        return entity;
    }

    /**
     * Finds entities where a given value falls within a numeric range defined by two properties.
     * For example, find a SeasonalIngredient where currentMonth >= startDate AND currentMonth <= endDate.
     *
     * @param startProp the name of the lower bound property on the entity
     * @param endProp   the name of the upper bound property on the entity
     * @param value     the value to check against the range
     * @return list of matching entities
     */
    public List<T> findByPropertyBetween(String startProp, String endProp, int value) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        query.where(
                builder.and(
                        builder.lessThanOrEqualTo(root.get(startProp), value),
                        builder.greaterThanOrEqualTo(root.get(endProp), value)
                )
        );

        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Finds entities whose date range wraps around the new year and includes the given month.
     * These are rows where startDate > endDate (e.g. startDate=11, endDate=2 for Nov–Feb).
     *
     * The match condition is: currentMonth >= startDate OR currentMonth < endDate,
     * which covers both ends of the wraparound.
     *
     * @param currentMonth the current month (1–12)
     * @return list of matching entities
     */
    public List<T> findWraparoundByMonth(int currentMonth) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        query.where(
                builder.and(
                        // Only consider rows where the range actually wraps (startDate > endDate)
                        builder.greaterThan(root.get("startDate"), root.get("endDate")),
                        builder.or(
                                builder.greaterThanOrEqualTo(root.get("startDate"), currentMonth),
                                builder.lessThan(root.get("endDate"), currentMonth)
                        )
                )
        );

        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }
}