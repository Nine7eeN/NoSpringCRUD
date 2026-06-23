package ui;

import dao.UserDAO;
import entities.User;
import exceptions.InvalidOptionException;
import exceptions.NotUniqueException;
import services.ValidateEntries;
import exceptions.UserNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu {
    private static final UserDAO userDAO = new UserDAO();

    public static void openMenu(){
        System.out.println("===SISTEMA DE GERENCIAMENTO DE USUARIOS===");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Listar Usuários");
        System.out.println("3. Procurar Usuário");
        System.out.println("4. Atualizar Usuário");
        System.out.println("5. Excluir Usuário");
        System.out.println("0. Sair");
    }

    public static void awaitConfirmation(Scanner sc){
        System.out.println("Pressione [Enter] para continuar.");
        sc.nextLine();
    }

    public static void chooseOption(String option, Scanner sc){

        switch (option) {
            case "1":
                try {
                    System.out.println("Insira o nome do usuário a ser registrado: ");
                    String userName = ValidateEntries.validateUsername(sc.nextLine());
                    System.out.println("Insira o Email do usuário a ser registrado: ");
                    String userEmail = ValidateEntries.validateEmail(sc.nextLine());
                    int savedID = userDAO.save(userName, userEmail);
                    System.out.println("Usuário " + userName + " salvo com ID #" + savedID);
                } catch (IllegalArgumentException e){
                    System.out.println("Erro: " + e.getMessage());
                } catch (NullPointerException | IllegalStateException | NoSuchElementException e){
                    System.out.println("Erro grave: " + e.getMessage());
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
                System.out.println("Insira o ID do usuário que deseja procurar: ");
                try {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    User user = userDAO.selectByID(id);
                    System.out.println("Usuário encontrado: \n" + user);
                } catch(UserNotFoundException e){
                    System.out.println("Erro: " + e.getMessage());
                } catch(NullPointerException | IllegalStateException | NoSuchElementException e){
                    System.out.println("Erro grave: " + e.getMessage());
                } catch(RuntimeException e){
                    System.out.println("Erro inesperado.");
                }
                break;
            case "4":
                System.out.println("Insira o ID do usuário que deseja atualizar: ");
                try  {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    System.out.println("Insira o novo nome de usuário: ");
                    String username = ValidateEntries.validateUsername(sc.nextLine());
                    System.out.println("Insira o novo email do usuário: ");
                    String email = ValidateEntries.validateEmail(sc.nextLine());
                    int updatedUserID = userDAO.update(id, username, email);
                    System.out.println("Usuário #" + updatedUserID + " atualizado com sucesso!");
                } catch (IllegalArgumentException | UserNotFoundException | NotUniqueException e){
                    System.out.println("Erro: " + e.getMessage());
                } catch(NullPointerException | IllegalStateException | NoSuchElementException e){
                    System.out.println("Erro grave: " + e.getMessage());
                } catch (RuntimeException e) {
                    System.out.println("Erro inesperado.");
                }
                break;
            case "5":
                System.out.println("Insira o ID do usuário que deseja excluir:");
                try {
                    int id = ValidateEntries.validateUserID(sc.nextLine());
                    int deletedUserID = userDAO.delete(id);
                    System.out.println("Usuário #" + deletedUserID + " excluído com sucesso!");
                } catch (IllegalArgumentException | UserNotFoundException | NotUniqueException e){
                    System.out.println("Erro: " + e.getMessage());
                } catch(NullPointerException | IllegalStateException | NoSuchElementException e){
                    System.out.println("Erro grave: " + e.getMessage());
                }catch (RuntimeException e) {
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
