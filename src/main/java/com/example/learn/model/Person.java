package com.example.learn.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;

    private double power;

    public Person(String name, int age, double power) {
        this.name = name;
        this.age = age;
        this.power = power;
    }


    @Override
    public String toString() {
        return String.format("Person: %s, power: %.2f.", name, power);
    }

}
