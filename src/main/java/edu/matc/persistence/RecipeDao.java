package edu.matc.persistence;

import edu.matc.entity.Recipe;
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
 * The type Recipe dao.
 */
public class RecipeDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Session factory.
     */
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get order by id
     *
     * @param id the id
     * @return the by id
     */
    public Recipe getById(int id) {
        Session session = sessionFactory.openSession();
        Recipe order = session.get( Recipe.class, id );
        session.close();
        return order;
    }

    /**
     * update order
     *
     * @param order Recipe to be updated
     */
    public void update(Recipe order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(order);
        transaction.commit();
        session.close();
    }

    /**
     * insert a new order
     *
     * @param order Recipe to be inserted
     * @return the int
     */
    public int insert(Recipe order) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
        id = order.getId();
        session.close();
        return id;
    }

    /**
     * Delete a order
     *
     * @param order Recipe to be deleted
     */
    public void delete(Recipe order) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(order);
        transaction.commit();
        session.close();
    }


    /**
     * Return a list of all orders
     *
     * @return All orders
     */
    public List<Recipe> getAll() {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from(Recipe.class);
        List<Recipe> orders = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of orders " + orders);
        session.close();

        return orders;
    }

    /**
     * Get order by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Curry")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property equal
     */
    public List<Recipe> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for recipe with " + propertyName + " = " + value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from(Recipe.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Recipe> orders = session.createSelectionQuery( query ).getResultList();

        session.close();
        return orders;
    }

    /**
     * Get order by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     *
     * @param propertyName the property name
     * @param value        the value
     * @return the by property like
     */
    public List<Recipe> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for order with {} = {}",  propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Recipe> query = builder.createQuery(Recipe.class);
        Root<Recipe> root = query.from( Recipe.class );
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Recipe> orders = session.createQuery( query ).getResultList();
        session.close();
        return orders;
    }
}
