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
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int kcal;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_to_food", joinColumns = @JoinColumn(name = "food_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> people = new ArrayList<>();

    public Food(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

}
