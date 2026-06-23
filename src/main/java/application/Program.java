package application;

import dao.UserDAO;
import exceptions.InvalidOptionException;
import ui.Menu;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String option = "";
        Scanner sc = new Scanner(System.in);

        do {
            try {
                Menu.openMenu();
                option = sc.nextLine();
                Menu.chooseOption(option, sc);
                if (!"0".equals(option)) Menu.awaitConfirmation(sc);
            } catch (InvalidOptionException e){
                System.out.println("Erro: " + e.getMessage());
            }
        } while (!"0".equals(option));

        sc.close();
    }
}