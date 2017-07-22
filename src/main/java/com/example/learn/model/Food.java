package com.example.learn.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int kcal;

    public Food(String name) {
        this.name = name;
    }
}
