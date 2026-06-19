package application;

import dao.UserDAO;
import entities.User;

import java.util.Scanner;

public class Menu {
    public static void openMenu(){
        Scanner sc = new Scanner(System.in);

        System.out.println("===SISTEMA DE GERENCIAMENTO DE USUARIOS===");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Atualizar Usuário");
        System.out.println("4. Excluir Usuário");
        System.out.println("0. Sair");
    }

    private static String read(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        return input;
    }

    public static void chooseOption(char option){
        UserDAO userDAO = new UserDAO();
        switch (option) {
            case '1':
                System.out.println("Insira os dados do usuário a ser registrado: ");
                System.out.println("Ex: nome, email");
                String[] data = read().split(",");
                User user = new User(null, data[0].trim(), data[1].trim());
                userDAO.save(user);
                break;
            case '2':
                System.out.println("Listando usuários...");
                userDAO.load();
                break;
            case '3':
                System.out.println("Insira o ID do usuário que deseja atualizar: ");
                try {
                    int id = Integer.parseInt(read());
                } catch (RuntimeException e) {
                    System.out.println("ID inválido. Tente novamente.");
                    e.printStackTrace();;
                }
                break;
            case '4':
                System.out.println("Insira o ID do usuário que deseja excluir:");
                try {
                    int id = Integer.parseInt(read());
                } catch (RuntimeException e) {
                    System.out.println("ID inválido. Tente novamente.");
                    e.printStackTrace();;
                }
                break;
            case '0':
                System.out.println("Saindo do sistema...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
