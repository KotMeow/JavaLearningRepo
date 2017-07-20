package com.example.learn.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double power;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Food> foods = new ArrayList<>();


    public Person(String name, double power) {
        this.name = name;
        this.power = power;
    }
}
