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
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

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
        person1.getFoods().add(food);
        person1.getFoods().add(food2);
        personRepository.save(person1);
        assertThat(personRepository.findAll()).hasSize(1);
        assertThat(foodRepository.findAll()).hasSize(2);
        assertThat(personRepository.findOne(person1.getId()).getFoods()).contains(food, food2);
    }

    @Test
    public void removePerson() throws Exception {
        person1.getFoods().add(food);
        person1.getFoods().add(food2);
        personRepository.save(person1);

        assertThat(personRepository.findOne(person1.getId()).getFoods()).contains(food, food2);

        personRepository.delete(person1.getId());

        assertThat(personRepository.findAll()).isEmpty();
        assertThat(foodRepository.findAll()).isEmpty();

    }
}
