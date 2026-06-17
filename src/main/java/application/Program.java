package application;

import dao.UserDAO;
import entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Program {
    public static void main(String[] args) {
        User user = new User(null, "maria", "maria@gmail.com");

        UserDAO userDAO = new UserDAO();
        userDAO.save(user);

        System.out.println("Salvo com id: " + user.getId());
    }
}
