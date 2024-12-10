package io.lyssn.assignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.lyssn.assignment.User.getConnection;

public class Message {
    Long messageId;
    String message;
    Long userId;

    public static List<Message> getAllMessagesByUsername(String firstName, String lastName) throws SQLException {
        // find the user by comparing username or find by ID
        User user = new User();
        List<Message> messages = new ArrayList<>();
        if (user.getfName().equals(firstName) && user.getName().equals(lastName)) {
            Long userId = user.getUserId();

            String query = "SELECT * FROM message WHERE userid = ?";
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setLong(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Long messageId = resultSet.getLong("messageId");
                        String message = resultSet.getString("message");
                        Long messageUserId = resultSet.getLong("userId");

                        messages.add(new Message(messageId, message, messageUserId));
                    }
                }
            }
        }
        return messages;
    }

    public Message(Long messageId, String message, Long userId) {
        this.messageId = messageId;
        this.message = message;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", message='" + message + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
