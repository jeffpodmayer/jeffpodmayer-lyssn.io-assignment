# Task 1
This is a simple Java application that interacts with a PostgreSQL database to manage user data. The application includes methods for connecting to the database, getting all users and updating a user. It is built in Java utilizing the JDBC API.

## Features
- **Database Connection**: Connects to a PostgreSQL database with connection 'jdbc:postgresql://localhost:5432/mydb'
- **User Management**:
  - Get all users
  - Retrieve users by their `userId`.
  - Update a user 
- **Testable**: JUnit tests are provided for verifying the functionality of the methods
- **Code Snippets**
  
'''java import domain.User;
// Get all users
Collection<User> users = User.getAllUsers();

// Retrieve user by userId
User user = User.getUserByUserId(12345);

// Update a user
user.fname = "Changed"
user.name = "Name"
User.updateUser(user);'''

## Requirements
- **Java 8 or later+**
- **PostgreSQL Database**
- **JUnit 5** for unit testing
- **IDE** (e.g., IntelliJ IDEA) 


# Task 2 - 
