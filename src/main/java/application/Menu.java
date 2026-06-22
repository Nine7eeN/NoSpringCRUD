package application;

import dao.UserDAO;
import entities.User;
import services.ValidateEntries;
import exceptions.UserNotFoundException;

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
                    String[] userData = ValidateEntries.validateUserData(sc.nextLine());
                    int savedID = userDAO.save(userData);
                    System.out.println("Usuário " + userData[0].trim() + " salvo com ID #" + savedID);
                } catch (IllegalArgumentException | IllegalStateException e){
                    System.out.println("Erro: " + e.getMessage() +
                                        "\nVoltando para a tela inicial...");;
                } catch (RuntimeException e){
                    System.out.println("Erro inesperado." +
                                        "\nVoltando para a tela inicial...");
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
                    System.out.println("Erro inesperado. \n" +
                            "Voltando para a tela inicial...");
                }
                break;
            case '3':
                System.out.println("Insira o ID do usuário que deseja atualizar: ");
                try  {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    System.out.println("Insira os novos dados do usuário: ");
                    String[] newData = ValidateEntries.validateUserData(sc.nextLine());
                    int updatedUserID = userDAO.update(id, newData[0].trim(), newData[1].trim());
                    System.out.println("Usuário #" + updatedUserID + " atualizado com sucesso!");
                } catch (IllegalArgumentException | UserNotFoundException e){
                    System.out.println("Erro: " + e.getMessage() +
                            "\nVoltando para a tela inicial...");
                }
                catch (RuntimeException e) {
                    System.out.println("Erro inesperado." +
                            "\nVoltando para a tela inicial...");
                }
                break;
            case '4':
                System.out.println("Insira o ID do usuário que deseja excluir:");
                try {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    int deletedUserID = userDAO.delete(id);
                    System.out.println("Usuário #" + deletedUserID + " excluído com sucesso!");
                } catch (IllegalArgumentException | UserNotFoundException e){
                    System.out.println("Erro: " + e.getMessage() +
                            "\nVoltando para a tela inicial...");
                }
                catch (RuntimeException e) {
                    System.out.println("Erro inesperado." +
                            "\nVoltando para a tela inicial...");
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
