package org.example;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StudentService {

    public void processStudents(List<Student> students) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getAverageGrade() < 3) {
                iterator.remove();
            } else {
                s.promote();
            }
        }
    }

    public void printStudents(Set<Student> students, int course) {
        System.out.println("--- Студенты на " + course + " курсе ---");
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println(s.getName());
            }
        }
    }
}

