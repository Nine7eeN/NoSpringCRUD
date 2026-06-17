package dao;

import db.ConnectionFactory;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public void save(User user){
        String sql = "INSERT INTO users (username, email) VALUES (? , ?)";

        try (Connection connection = connectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();

            try (ResultSet result = stmt.getGeneratedKeys()) {
                if (result.next()) {
                    user.setId(result.getInt(1));
                }
            }
        }catch(SQLException e) {
                throw new RuntimeException("Erro ao salvar usuário, e");
            }
        }
    }
