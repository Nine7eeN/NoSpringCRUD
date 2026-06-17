package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/nospringcrud";
    private static final String USER = "root";
    private static final String PASSWORD = "uz123";

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e){
            throw new RuntimeException("Erro ao conectar no banco de dados: " + e.getMessage());
        }
    }
}
