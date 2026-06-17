package application;

import dao.UserDAO;
import entities.User;

public class Program {
    static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        //SALVANDO USUARIO
/*
        User user = new User(null, "carlosrosa3", "carlos3.rosa@gmail.com");
        userDAO.save(user);
*/
        //CARREGANDO USUARIOS
/*
        for (User u : userDAO.load()) {
            System.out.println(u);
        }
*/
        //ALTERANDO USUARIO
/*
        userDAO.update(1, "joao", "joao@gmail.com");
*/
        // DELETANDO USUARIO
/*
            userDAO.delete(1);
*/
    }
}