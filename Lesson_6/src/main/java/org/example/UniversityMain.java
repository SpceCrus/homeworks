package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class UniversityMain {

    public static void main(String[] args) {
        StudentService service = new StudentService();

        List<Student> students = new ArrayList<>();
        students.add(new Student("Иван", "A1", 1, Arrays.asList(4, 5, 4)));
        students.add(new Student("Петя", "B2", 1, Arrays.asList(2, 2, 3)));
        students.add(new Student("Анна", "A1", 1, Arrays.asList(5, 5, 5)));
        students.add(new Student("Сема", "A1", 2, Arrays.asList(3, 3, 4)));
        students.add(new Student("Вася", "B2", 2, Arrays.asList(5, 4, 3)));
        students.add(new Student("Юля", "A1", 1, Arrays.asList(2, 3, 4)));

        service.processStudents(students);

        service.printStudents(new HashSet<>(students), 2);
    }
}
