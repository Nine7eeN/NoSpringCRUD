package application;

import dao.UserDAO;
import entities.User;

import java.util.Scanner;

public class Program {
    static void main(String[] args) {
        Character option = '0';
        Scanner sc = new Scanner(System.in);
        do {
            Menu.openMenu();
            option = sc.nextLine().toUpperCase().charAt(0);
            Menu.chooseOption(option, sc);
        } while (!(option.equals('0')));
        sc.close();
    }
}