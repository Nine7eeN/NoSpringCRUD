package application;

import dao.UserDAO;
import entities.User;

import java.util.List;
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

    public static void chooseOption(char option, Scanner sc){
        UserDAO userDAO = new UserDAO();

        switch (option) {
            case '1':
                try {
                    System.out.println("Insira os dados do usuário a ser registrado: ");
                    System.out.println("Ex: nome, email");
                    String[] data = sc.nextLine().split(",");
                    User userToSave = new User(null, data[0].trim(), data[1].trim());
                    userDAO.save(userToSave);
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Erro: você precisa inserir ao menos dois dados para cadastrar um usuário!");
                    System.out.println("Voltando para a tela inicial...");
                } catch (RuntimeException e){
                    System.out.println("ERRO!");
                    System.out.println("Tipo:" + e.getClass().getName());
                    System.out.println("Mensagem" + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Voltando para a tela inicial...");
                }
                break;
            case '2':
                try {
                    System.out.println("Listando usuários...");
                    List<User> userList = userDAO.load();
                    for (User user : userList) {
                        System.out.println(user);
                    }
                    System.out.println("Aperte [Enter] para continuar.");
                    sc.nextLine();
                } catch (RuntimeException e) {
                    System.out.println("ERRO!");
                    System.out.println("Tipo:" + e.getClass().getName());
                    System.out.println("Mensagem" + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Voltando para a tela inicial...");
                }
                break;
            case '3':
                System.out.println("Insira o ID do usuário que deseja atualizar: ");
                try  {
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Insira os novos dados do usuário: ");
                    System.out.println("Ex: nome, email");
                    String[] newData = sc.nextLine().split(",");
                    userDAO.update(id, newData[0].trim(), newData[1].trim());
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Erro: você precisa inserir ao menos dois dados para atualizar um usuário!");
                    System.out.println("Pressione [Enter] para voltar para a tela inicial.");
                    sc.nextLine();
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Formato de ID inválido!");
                    System.out.println("Pressione [Enter] para voltar para a tela inicial.");
                    sc.nextLine();
                } catch (RuntimeException e) {
                    System.out.println("ERRO!");
                    System.out.println("Tipo:" + e.getClass().getName());
                    System.out.println("Mensagem" + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Voltando para a tela inicial...");
                }
                break;
            case '4':
                System.out.println("Insira o ID do usuário que deseja excluir:");
                try {
                    int id = Integer.parseInt(sc.nextLine());
                    userDAO.delete(id);
                } catch (NumberFormatException e){
                    System.out.println("Erro: Formato de ID inválido!");
                    System.out.println("Pressione [Enter] para voltar para a tela inicial.");
                    sc.nextLine();
                } catch (RuntimeException e) {
                    System.out.println("ERRO!");
                    System.out.println("Tipo:" + e.getClass().getName());
                    System.out.println("Mensagem" + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Voltando para a tela inicial...");
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
