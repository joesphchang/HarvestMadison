package edu.matc.persistence;

import edu.matc.entity.User;
import edu.matc.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    GenericDao userDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDao = new GenericDao(User.class);
    }

    @Test
    void getUserByIdSuccess() {
        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("Joey", retrievedUser.getFirstName());
    }

   @Test
   void updateUserSuccess() {
        User userToUpdate = (User)userDao.getById(2);
        userToUpdate.setLastName("Jordan");
        userDao.update(userToUpdate);

        User actualUser = (User)userDao.getById(2);
        assertEquals("Jordan", actualUser.getLastName());
   }

   @Test
   void insertUserSuccess() {
        User userToInsert = new User("Liam", "Pullian", "lpullian");
        int insertedUserId = userDao.insert(userToInsert);
        assertNotEquals(0, insertedUserId);
        User insertedUser = (User)userDao.getById(insertedUserId);
        assertEquals("Liam", insertedUser.getFirstName());
   }

    @Test
    void deleteUser() {
        userDao.delete(userDao.getById(3));
        assertNull(userDao.getById(3));
    }

    @Test
    void getAllUsers() {
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());
    }

    @Test
    void getByPropertyEqual() {
        List<User> users = userDao.findByPropertyEqual("lastName", "Baker");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getId());
    }
}
