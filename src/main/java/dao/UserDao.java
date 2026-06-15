package dao;

import db.ConnectionFactory;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UserDao {
    public void save(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error saving user", e);
        }
    }
}
