package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {
    Long userId;
    String fName;
    String name;
    Timestamp signUpdate;

    // Constructor
    public User(Integer userId, String fName, String name, Timestamp signUpdate) {
        this.userId = userId;
        this.fName = fName;
        this.name = name;
        this.signUpdate = signUpdate;
    }

    // Getters & Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getSignUpdate() {
        return signUpdate;
    }

    public void setSignUpdate(Timestamp signUpdate) {
        this.signUpdate = signUpdate;
    }

    @Override
    public String toString() {
        return STR."User{userId=\{userId}, fName='\{fName}\{'\''}, name='\{name}\{'\''}, signUpdate=\{signUpdate}\{'}'}";
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        return DriverManager.getConnection(url);
    }

    public static Collection<User> getAllUsers(){
        Collection<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        return users;
    }

    public static User updateUser(User user){

        return user;
    }

    public static User getUserByUserId(Long userId){
        String query = "SELECT * FROM users WHERE userid ?";


        return null;
    }

}
