package com.example.learn.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @MapKey
    private Map<FoodType, Food> orders = new HashMap<>();


    public Person(String name, double power) {
        this.name = name;
        this.power = power;
    }
}
