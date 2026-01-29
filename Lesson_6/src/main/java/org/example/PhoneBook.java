package org.example;

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> book = new HashMap<>();

    public void add(String surname, String phone) {
        book.computeIfAbsent(surname, k -> new ArrayList<>()).add(phone);
    }

    public List<String> get(String surname) {
        return book.getOrDefault(surname, new ArrayList<>());
    }
}

