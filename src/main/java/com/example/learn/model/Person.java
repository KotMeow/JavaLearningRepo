package com.example.learn.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Person.findByName", query = "from Person p where p.name = ?1")
public class Person {

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
