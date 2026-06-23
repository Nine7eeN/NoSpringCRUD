package application;

import dao.UserDAO;
import entities.User;
import exceptions.InvalidOptionException;
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

    public static void awaitConfirmation(Scanner sc){
        System.out.println("Pressione [Enter] para continuar.");
        sc.nextLine();
    }

    public static void chooseOption(String option, Scanner sc){
        UserDAO userDAO = new UserDAO();

        switch (option) {
            case "1":
                try {
                    System.out.println("Insira o nome do usuário a ser registrado: ");
                    String userName = ValidateEntries.validateUsername(sc.nextLine());
                    System.out.println("Insira o Email do usuário a ser registrado: ");
                    String userEmail = ValidateEntries.validateEmail(sc.nextLine());
                    int savedID = userDAO.save(userName, userEmail);
                    System.out.println("Usuário " + userName + " salvo com ID #" + savedID);
                } catch (IllegalArgumentException | IllegalStateException e){
                    System.out.println("Erro: " + e.getMessage());
                } catch (RuntimeException e){
                    System.out.println("Erro inesperado.");
                }
                break;
            case "2":
                try {
                    System.out.println("Listando usuários...");
                    List<User> userList = userDAO.load();
                    if (userList.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado.");
                    } else {
                        for (User user : userList) {
                            System.out.println(user);
                        }
                    }
                } catch (RuntimeException e) {
                    System.out.println("Erro inesperado.");
                }
                break;
            case "3":
                System.out.println("Insira o ID do usuário que deseja atualizar: ");
                try  {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    System.out.println("Insira o novo nome de usuário: ");
                    String username = ValidateEntries.validateUsername(sc.nextLine());
                    System.out.println("Insira o novo email do usuário: ");
                    String email = ValidateEntries.validateEmail(sc.nextLine());
                    int updatedUserID = userDAO.update(id, username, email);
                    System.out.println("Usuário #" + updatedUserID + " atualizado com sucesso!");
                } catch (IllegalArgumentException | UserNotFoundException e){
                    System.out.println("Erro: " + e.getMessage());
                }
                catch (RuntimeException e) {
                    System.out.println("Erro inesperado.");
                }
                break;
            case "4":
                System.out.println("Insira o ID do usuário que deseja excluir:");
                try {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    int deletedUserID = userDAO.delete(id);
                    System.out.println("Usuário #" + deletedUserID + " excluído com sucesso!");
                } catch (IllegalArgumentException | UserNotFoundException e){
                    System.out.println("Erro: " + e.getMessage());
                }
                catch (RuntimeException e) {
                    System.out.println("Erro inesperado.");
                }
                break;
            case "0":
                System.out.println("Saindo do sistema...");
                break;
            default:
                throw new InvalidOptionException("Opção inválida. Tente novamente.");
        }
    }
}
