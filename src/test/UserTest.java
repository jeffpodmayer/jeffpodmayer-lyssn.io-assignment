package test;

import io.lyssn.assignment.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testDatabaseConnection() throws SQLException {
        try (Connection connection = User.getConnection()) {
            assertNotNull(connection);
            System.out.println("Success!");
        }
    }

    @BeforeEach
    void createUsersForTest() throws SQLException {
        try (Connection connection = User.getConnection()){
            Statement stmt = connection.createStatement();

            stmt.execute("INSERT INTO users (userId, fname, name, signupdate) VALUES (1, 'Jeff', 'Podmayer', '2024-11-22 09:00:00')");
            stmt.execute("INSERT INTO users (userId, fname, name, signupdate) VALUES (2, 'John', 'Meade', '2024-11-22 09:10:00')");
            stmt.execute("INSERT INTO users (userId, fname, name, signupdate) VALUES (3, 'Dan', 'Ford', '2024-11-22 09:15:00')");
            stmt.execute("INSERT INTO users (userId, fname, name, signupdate) VALUES (4, 'Carolyn', 'Blessing', '2024-11-22 09:20:00')");
            stmt.execute("INSERT INTO users (userId, fname, name, signupdate) VALUES (5, 'Malcolm', 'Griffiths', '2024-11-22 09:30:00')");
            }
    }

    @AfterEach
    void deleteCreatedUsers() throws SQLException {
        try (Connection connection = User.getConnection()) {
            Statement stmt = connection.createStatement();

            stmt.execute("DELETE FROM users");
            System.out.println("All users deleted.");
        }
    }

    @Test
    void testGetAllUsers() throws SQLException {
        Collection<User> users = User.getAllUsers();
        assertNotNull(users);
        assertEquals(5,users.size());
    }

    @Test
    void testGetUserByUserId() throws SQLException {
        User user = User.getUserByUserId(5L);
        assertNotNull(user);
        System.out.println(user);

        assertEquals("Malcolm", user.getfName());
        assertEquals("Griffiths", user.getName());
        assertEquals(5L, user.getUserId());
    }

    @Test
    void testUpdateUser() throws SQLException {
        User existingUser = User.getUserByUserId(2L);
        assertNotNull(existingUser, "User exists in database!");

        existingUser.setfName("Jonathan");
        boolean resultOfUpdate = User.updateUser(existingUser);
        assertTrue(resultOfUpdate);

        User updatedUser = User.getUserByUserId(2L);
        assertEquals("Jonathan", updatedUser.getfName());
        System.out.println(updatedUser);
    }
}
