package com.example.learn;

import com.example.learn.model.Food;
import com.example.learn.model.Person;
import com.example.learn.service.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Person person1;
    private Person person2;
    private Person person3;

    @Before
    public void setUp() throws Exception {
        person1 = new Person("Vader", "Poland", "Gdynia", 5.5);
        person2 = new Person("Yoda", "Poland", "Sopot", 9.5);
        person3 = new Person("Luke", "France", "Paris", 2.0);
    }

    @Test
    public void savePerson() throws Exception {
        personRepository.save(person1);

        Person retrived = personRepository.findOne(person1.getId());
        assertThat(retrived).isEqualTo(person1);
        assertThat(personRepository.findAll()).hasSize(1).containsOnly(person1);
    }

    @Test
    public void findByCountryOrCity() {
        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findByCountryOrCity("Poland", "Gdynia");
        assertThat(found).hasSize(2).containsOnly(person1, person2);
    }

    @Test
    public void findByCountryAndCity() throws Exception {
        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findByCountryAndCity("Poland", "Gdynia");

        assertThat(found).hasSize(1).containsOnly(person1);
    }

    @Test
    public void findByCountryNot() throws Exception {
        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findByCountryNot("Poland");

        assertThat(found).containsOnly(person3);

    }

    @Test
    public void findByCountryContaining() throws Exception {
        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findByCountryContaining("an");

        assertThat(found).hasSize(3).contains(person1, person3);

    }

    @Test
    public void findByPowerGreaterThen() throws Exception {

        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findByPowerGreaterThan(5);

        assertThat(found).containsOnly(person1, person2);
    }

    @Test
    public void findByBirthDayAfter() throws Exception {
        person1.setBirthDay(LocalDate.of(2010, 10, 10));
        person3.setBirthDay(LocalDate.of(2011, 10, 10));
        person2.setBirthDay(LocalDate.of(2012, 10, 10));

        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository
                .findByBirthDayAfter(LocalDate.of(2010, 11, 11));
        assertThat(found).containsOnly(person2, person3);
    }

    @Test
    public void findByCountryIn() throws Exception {
        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findByCountryIn(Arrays.asList("Poland", "France"));

        assertThat(found).containsOnly(person1, person2, person3);

    }

    @Test
    public void findByFoodName() throws Exception {
        person1.getFood().add(new Food("Pie"));
        person2.getFood().add(new Food("Cake"));
        person3.getFood().add(new Food("Pie"));

        personRepository.save(Arrays.asList(person1,person2,person3));


        List<Person> found = personRepository.findByFoodName("Pie");
        assertThat(found).contains(person1, person3);
    }


    @Test
    public void findByFoodNameIn() throws Exception {
        person1.getFood().add(new Food("Pie"));
        person2.getFood().add(new Food("Cake"));
        person3.getFood().add(new Food("Steak"));

        personRepository.save(Arrays.asList(person1,person2,person3));


        List<Person> found = personRepository.findByFoodNameIn(Arrays.asList("Pie", "Cake"));
        assertThat(found).contains(person1, person2);
    }

    @Test
    public void findAllOrderByPower() throws Exception {

        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.findAllByOrderByPowerDesc();

        log.warn(String.valueOf(found));
        assertThat(found).containsExactly(person2, person1, person3);
    }

    @Test
    public void countDistinctByCountry() throws Exception {
        personRepository.save(Arrays.asList(person1,person2,person3));

        int found = personRepository.countByCountry("Poland");

        assertThat(found).isEqualTo(2);

    }

    @Test
    public void queryByCountryAndPowerRange() throws Exception {
        personRepository.save(Arrays.asList(person1,person2,person3));

        List<Person> found = personRepository.queryByCountryAndPowerRange("Poland", 6, 10);

        assertThat(found).hasSize(1).containsOnly(person2);
    }
}
