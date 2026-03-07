package com.joeychang.persistence;

import com.joeychang.entity.Recipe;
import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.entity.User;
import com.joeychang.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User dao test.
 */
public class UserDaoTest {

    /**
     * The User dao.
     */
    GenericDao<User> userDao;
    /**
     * The Recipe dao.
     */
    GenericDao<Recipe> recipeDao;
    /**
     * The Seasonal ingredient dao.
     */
    GenericDao<SeasonalIngredient> seasonalIngredientDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDao = new GenericDao<>(User.class);
        recipeDao = new GenericDao<>(Recipe.class);
        seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
    }

    /**
     * Gets user by id success.
     */
    @Test
    void getUserByIdSuccess() {
        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("Joey", retrievedUser.getFirstName());
    }

    /**
     * Update user success.
     */
    @Test
   void updateUserSuccess() {
        User userToUpdate = (User)userDao.getById(2);
        userToUpdate.setLastName("Jordan");
        userDao.update(userToUpdate);

        User actualUser = (User)userDao.getById(2);
        assertEquals("Jordan", actualUser.getLastName());
   }

    /**
     * Insert user success.
     */
    @Test
   void insertUserSuccess() {
        User userToInsert = new User("Liam", "Pullian", "lpullian");
        int insertedUserId = userDao.insert(userToInsert);
        assertNotEquals(0, insertedUserId);
        User insertedUser = (User)userDao.getById(insertedUserId);
        assertEquals("Liam", insertedUser.getFirstName());
   }

    /**
     * Delete user.
     */
    @Test
    void deleteUser() {
        User userToInsert = new User("Test", "User", "testuser");
        int id = userDao.insert(userToInsert);
        User userToDelete = userDao.getById(id);
        userDao.delete(userToDelete);
        assertNull(userDao.getById(id));
    }

    /**
     * Gets all users.
     */
    @Test
    void getAllUsers() {
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());
    }

    /**
     * Gets by property equal.
     */
    @Test
    void getByPropertyEqual() {
        List<User> users = userDao.findByPropertyEqual("lastName", "Baker");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getId());
    }

    /**
     * Delete user with recipe.
     */
//    @Test
//    void deleteUserWithRecipe() {
//        User user = userDao.getById(1);
//        assertNotNull(user);
//        int userId = user.getId();
//
//        int countBefore = recipeDao.findByPropertyEqual("user", user).size();
//        assertTrue(countBefore > 0, "User 1 should have recipes in this dump");
//        userDao.delete(user);
//
//        assertNull(userDao.getById(userId));
//
//        List<Recipe> orphanedRecipes = recipeDao.findByPropertyEqual("user", user);
//        assertEquals(0, orphanedRecipes.size(), "Recipes associated with this user should be gone");
//    }
}
