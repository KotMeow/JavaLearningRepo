package com.example.learn.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Food food;


    public Person(String name, double power) {
        this.name = name;
        this.power = power;
    }
}
