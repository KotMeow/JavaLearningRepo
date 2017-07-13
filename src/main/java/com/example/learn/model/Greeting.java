package com.example.learn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {

    private String name;
    private int age;

    @Override
    public String toString() {
        return "Greeting{" + "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
