package com.example.learn.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String country;
    private String city;
    private LocalDate birthDay;
    private double power;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Food> food = new ArrayList<>();

    public Person(String name, double power) {
        this.name = name;
        this.power = power;
    }

    public Person(String name, String country, String city, double power) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.power = power;
    }
}
