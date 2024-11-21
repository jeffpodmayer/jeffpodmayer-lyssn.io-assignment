package domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {
    Long userId;
    String fName;
    String name;
    Timestamp signUpDate;

    // Constructor
    public User(Long userId, String fName, String name, Timestamp signUpDate) {
        this.userId = userId;
        this.fName = fName;
        this.name = name;
        this.signUpDate = signUpDate;
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
        return signUpDate;
    }

    public void setSignUpdate(Timestamp signUpdate) {
        this.signUpDate = signUpDate;
    }

    @Override
    public String toString() {
        return STR."User{userId=\{userId}, fName='\{fName}\{'\''}, name='\{name}\{'\''}, signUpdate=\{signUpDate}\{'}'}";
    }

   //Method to connect to a database
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        return DriverManager.getConnection(url);
    }

    public static Collection<User> getAllUsers() throws SQLException {
        Collection<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                long userid = rs.getLong("userid");
                String fname = rs.getString("fname");
                String name = rs.getString("name");
                Timestamp signupdate = rs.getTimestamp("signupdate");
                users.add(new User(userid, fname, name, signupdate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static Boolean updateUser(User user) throws SQLException {
        String updateQuery = "UPDATE users SET fname = ?, name = ?, signupdate = ? WHERE userid = ?";
        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
            preparedStatement.setString(1, user.getfName());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setTimestamp(3, user.getSignUpdate());
            preparedStatement.setLong(4, user.getUserId());

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getUserByUserId(Long userId){
        String query = "SELECT * FROM users WHERE userid ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,userId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Long userid = resultSet.getLong("userid");
                    String fName = resultSet.getString("fName");
                    String name = resultSet.getString("name");
                    Timestamp signupdate = resultSet.getTimestamp("signupdate");

                    return new User(userId, fName, name, signupdate);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
