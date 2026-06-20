package application;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        char option = ' ';
        Scanner sc = new Scanner(System.in);

        do {
            try {
                Menu.openMenu();
                option = sc.nextLine().toUpperCase().charAt(0);
                Menu.chooseOption(option, sc);
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("Escolha uma das opções.");
            }
        } while (option != '0');

        sc.close();
    }
}