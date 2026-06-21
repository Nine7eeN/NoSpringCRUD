package dao;

import db.ConnectionFactory;
import entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Usuário " + user.getUsername() + ", " + "ID #" + user.getId() + " salvo com sucesso.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Dados duplicados. O Email ou nome de usuário já está em uso.", e);
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

    public void update(Integer id, String newName, String newEmail){
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";

        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setString(2, newEmail);
            stmt.setInt(3, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário #" + id + " atualizado com sucesso.");
                return;
            }
            System.out.println("Usuário #" + id + " não encontrado.");
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new RuntimeException("Dados duplicados. O Email ou nome de usuário já está em uso.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void delete(Integer id){
        String sql = "DELETE FROM users WHERE id = ?";

        try(Connection connection = connectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário #" + id + " deletado com sucesso.");
                return;
            }
            System.out.println("Usuário #" + id + " não encontrado.");
        } catch (SQLException e){
            throw new RuntimeException ("Erro ao deletar usuário.", e);
        }
    }
}