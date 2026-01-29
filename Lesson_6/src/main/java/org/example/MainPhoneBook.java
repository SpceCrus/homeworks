package org.example;

import java.util.List;

public class MainPhoneBook {

    public static void main(String[] args) {
        PhoneBook myBook = new PhoneBook();

        myBook.add("Иванов", "+7-911-111-11-11");
        myBook.add("Петров", "+7-922-222-22-22");
        myBook.add("Иванов", "+7-933-333-33-33");
        myBook.add("Сидоров", "+7-944-444-44-44");

        System.out.println("-----Поиск контактов ----");

        printContact(myBook, "Иванов");
        printContact(myBook, "Петров");
        printContact(myBook, "Unknown");
    }

    public static void printContact(PhoneBook pb, String surname) {
        List<String> numbers = pb.get(surname);
        if (numbers.isEmpty()) {
            System.out.println(surname + ": Контакт не найден");
        } else {
            System.out.println(surname + ": " + numbers);
        }
    }
}