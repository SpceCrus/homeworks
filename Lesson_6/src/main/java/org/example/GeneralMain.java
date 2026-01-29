package org.example;

import java.util.Scanner;

public class GeneralMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что запустить? 1 - Студенты, 2 - Справочник");
        int choice = scanner.nextInt();

        if (choice == 1) {
            UniversityMain.main(args);
        } else {
            MainPhoneBook.main(args);
        }
    }
}