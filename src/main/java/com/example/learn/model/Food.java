package com.example.learn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person person;

    public Food(String name, int kcal) {
        this.name = name;
        this.kcal = kcal;
    }

}
