package application;

import dao.UserDAO;
import entities.User;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        char option = '0';
        Scanner sc = new Scanner(System.in);

        do {
            Menu.openMenu();
            option = sc.nextLine().toUpperCase().charAt(0);
            Menu.chooseOption(option, sc);
        } while (option != '0');

        sc.close();
    }
}