package com.example.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting implements Comparable<Greeting> {

    private String name;
    private int age;
    private int dateOfBirth;

    public Greeting(String name, int age) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = LocalDate.now().getYear() - age;
    }

    public void setAge(int age) {
        this.age = age;
        this.dateOfBirth = LocalDate.now().getYear() - age;
    }

    @Override
    public String toString() {
        return java.lang.String.format("Greeting{name='%s', age=%d}", name, age);
    }

    @Override
    public int compareTo(Greeting o) {
        return 0;
    }
}
