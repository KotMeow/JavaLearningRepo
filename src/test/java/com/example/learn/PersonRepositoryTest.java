package com.example.learn;

import com.example.learn.model.Food;
import com.example.learn.model.Person;
import com.example.learn.service.FoodRepository;
import com.example.learn.service.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FoodRepository foodRepository;
//    @MockBean
//    private PersonRepository personRepositoryMock;

    private Person person1;
    private Person person2;
    private Food food;
    private Food food2;

    @Before
    public void init() {
        food = new Food("Cherry Pie", 250);
        food2 = new Food("Apple Pie", 440);
        person1 = new Person("Yoda", 4.44);
        person2 = new Person("Vader", 8);
    }

    @Test
    public void savePerson() throws Exception {
        personRepository.save(person1);
        assertThat(personRepository.findAll()).hasSize(1);
    }

    @Test
    public void saveFood() throws Exception {
        foodRepository.save(food);
        assertThat(foodRepository.findAll()).hasSize(1);
    }

    @Test
    public void savePersonWithFood() throws Exception {

        food.getPeople().add(person1);
        food.getPeople().add(person2);
        food2.getPeople().add(person1);
        person1.getFood().add(food);
        person1.getFood().add(food2);
        person2.getFood().add(food);
        personRepository.save(Arrays.asList(person1, person2));

        assertThat(personRepository.findOne(person1.getId()).getFood()).containsOnly(food, food2);
        assertThat(personRepository.findOne(person2.getId()).getFood()).containsOnly(food);
        assertThat(foodRepository.findOne(food.getId()).getPeople()).containsOnly(person1, person2);
        assertThat(foodRepository.findOne(food2.getId()).getPeople()).containsOnly(person1);
        assertThat(personRepository.findAll()).hasSize(2);
        assertThat(foodRepository.findAll()).hasSize(2);

    }
}
