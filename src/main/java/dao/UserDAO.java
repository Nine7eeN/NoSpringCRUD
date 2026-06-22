package dao;

import db.ConnectionFactory;
import entities.User;
import exceptions.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final ConnectionFactory connectionFactory = new ConnectionFactory();

    public int save(String username, String email){
        String sql = "INSERT INTO users (username, email) VALUES (? , ?)";

        try (Connection connection = connectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.executeUpdate();

            try (ResultSet result = stmt.getGeneratedKeys()) {
                if (result.next()) {
                    return result.getInt(1);
                }
            }
            throw new IllegalStateException("O banco não retornou ID gerado.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new IllegalArgumentException("Dados duplicados. O Email ou nome de usuário já está em uso.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public List<User> load(){
        String sql = "SELECT id, username, email FROM users";
        List<User> users = new ArrayList<>();
        try(Connection connection  = connectionFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery()) {
            while (results.next()) {
                User user = new User();
                user.setId(results.getInt("id"));
                user.setUsername(results.getString("username"));
                user.setEmail(results.getString("email"));
                users.add(user);
            }
        } catch (SQLException e){
            throw new RuntimeException("Falha ao carregar usuários: "+ e.getMessage());
        }
        return users;
    }

    public int update(Integer id, String newName, String newEmail){
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return id;
            }
            throw new UserNotFoundException("Usuário #" + id + " não encontrado.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Dados duplicados. O Email ou nome de usuário já está em uso.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public int delete(Integer id){
        String sql = "DELETE FROM users WHERE id = ?";

        try(Connection connection = connectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return id;
            }
            throw new UserNotFoundException("Usuário #" + id + " não encontrado.");
        } catch (SQLException e){
            throw new RuntimeException ("Erro ao deletar usuário.", e);
        }
    }
}