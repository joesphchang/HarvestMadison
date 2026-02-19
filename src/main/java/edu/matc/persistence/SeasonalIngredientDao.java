package edu.matc.persistence;

import edu.matc.entity.SeasonalIngredient;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

/**
 * The type Seasonal ingredient dao.
 */
public class SeasonalIngredientDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    public SeasonalIngredient getById(int id) {
        Session session = sessionFactory.openSession();
        SeasonalIngredient seasonalIngredient = session.get( SeasonalIngredient.class, id );
        session.close();
        return seasonalIngredient;
    }

    /**
     * Update.
     *
     * @param seasonalIngredient the seasonal ingredient
     */
    public void update(SeasonalIngredient seasonalIngredient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(seasonalIngredient);
        transaction.commit();
        session.close();
    }

    /**
     * Insert int.
     *
     * @param seasonalIngredient the seasonal ingredient
     * @return the int
     */
    public int insert(SeasonalIngredient seasonalIngredient) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(seasonalIngredient);
        transaction.commit();
        id = seasonalIngredient.getId();
        session.close();
        return id;
    }

    /**
     * Delete.
     *
     * @param seasonalIngredient the seasonal ingredient
     */
    public void delete(SeasonalIngredient seasonalIngredient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(seasonalIngredient);
        transaction.commit();
        session.close();
    }


    /**
     * Gets all.
     *
     * @return the all
     */
    public List<SeasonalIngredient> getAll() {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SeasonalIngredient> query = builder.createQuery(SeasonalIngredient.class);
        Root<SeasonalIngredient> root = query.from(SeasonalIngredient.class);
        List<SeasonalIngredient> seasonalIngredients = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of seasonalIngredients " + seasonalIngredients);
        session.close();

        return seasonalIngredients;
    }

    /**
     * Gets by property equal.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<SeasonalIngredient> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for seasonalIngredient with " + propertyName + " = " + value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SeasonalIngredient> query = builder.createQuery(SeasonalIngredient.class);
        Root<SeasonalIngredient> root = query.from(SeasonalIngredient.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<SeasonalIngredient> seasonalIngredients = session.createSelectionQuery( query ).getResultList();

        session.close();
        return seasonalIngredients;
    }

    /**
     * Gets by property like.
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<SeasonalIngredient> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for seasonalIngredient with {} = {}",  propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SeasonalIngredient> query = builder.createQuery(SeasonalIngredient.class);
        Root<SeasonalIngredient> root = query.from( SeasonalIngredient.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<SeasonalIngredient> seasonalIngredients = session.createQuery( query ).getResultList();
        session.close();
        return seasonalIngredients;
    }
}
