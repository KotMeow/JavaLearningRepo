package com.example.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private int age;
    private double power;

    @Override
    public String toString() {
        return String.format("Person: %s, power: %.2f.", name, power);
    }

}
