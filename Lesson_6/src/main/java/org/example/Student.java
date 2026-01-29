package org.example;

import java.util.List;

public class Student {
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades) {
    this.name = name;
    this.group = group;
    this.course = course;
    this.grades = grades;
    }

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
        }

    public String getName() {
        return name; }
    public int getCourse() {
        return course; }

    public void promote() {
        this.course++;
    }

}





