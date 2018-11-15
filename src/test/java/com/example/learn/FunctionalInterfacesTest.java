package com.example.learn;

import com.example.learn.model.Person;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class FunctionalInterfacesTest {


    private List<Person> persons = Arrays.asList(
            new Person("Yoda", 24, 4.44),
            new Person("R2D2", 74, 8.0),
            new Person("Sith", 44, 2.0),
            new Person("Vader", 55, 9.5),
            new Person("Leia", 55, 7.0),
            new Person("Luke", 88, 7.0));
    private Person vader;


    //@Before - before each test, @BeforeClass - once before all
    @Before
    public void before() {
        vader = new Person("Vader", 55, 9.5);
    }

    @Test
    public void predicateTest() {
        Predicate<Person> checkPower = p -> p.getPower() >= 5;
        Predicate<Person> checkAge = p -> p.getAge() >= 59;
        assertThat(checkPower.or(checkAge).test(vader)).isEqualTo(true);
        assertThat(checkAge.test(vader)).isEqualTo(false);
    }

    @Test
    public void functionTest() {
        Function<Person, Integer> getBirthday = p -> LocalDate.now().getYear() - p.getAge();

        List<Integer> birthdays = persons.stream().map(getBirthday).collect(Collectors.toList());
        System.out.print(birthdays);
    }

    @Test
    public void consumerTest() {
        Consumer<Person> greeter = (p) -> System.out.printf("Hello, %s%n", p.getName());
        greeter.accept(vader);
    }

    @Test
    public void comparatorTest() {
        Comparator<Person> compByName = Comparator.comparing(Person::getName);
        String names = persons.stream().sorted(compByName).map(Person::getName).collect(Collectors.joining(", "));
        assertThat(names).containsSequence("Leia", "R2D2" ,"Yoda");
    }
}
