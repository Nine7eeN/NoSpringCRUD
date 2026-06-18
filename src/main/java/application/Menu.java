package application;

import dao.UserDAO;
import entities.User;

import java.util.Scanner;

public class Menu {
    public static void openMenu(){
        System.out.println("===SISTEMA DE GERENCIAMENTO DE USUARIOS===");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Atualizar Usuário");
        System.out.println("4. Excluir Usuário");
        System.out.println("0. Sair");
    }

    public static void chooseOption(){
        Scanner sc = new Scanner(System.in);
        char option = sc.next().toUpperCase().charAt(0);
        switch (option) {
            case '1':
                System.out.println("Insira os dados do usuário a ser registrado: ");
                System.out.println("Ex: nome, email");
                String[] data = sc.nextLine().replace(" ", "").split(",");
                User user = new User(null, data[0], data[1]);
                UserDAO userDAO = new UserDAO();
                userDAO.save(user);
                break;
            case '2':
                System.out.println("Listando usuários...");
                break;
            case '3':
                System.out.println("Insira o ID do usuário que deseja atualizar: ");
                break;
            case '4':
                System.out.println("Insira o ID do usuário que deseja excluir:");
                break;
            case '0':
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }

        sc.close();
    }
}
