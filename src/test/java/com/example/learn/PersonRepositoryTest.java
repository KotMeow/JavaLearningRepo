package com.example.learn;

import com.example.learn.model.Food;
import com.example.learn.model.FoodType;
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
//@Transactional
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
        food.setFoodType(FoodType.DINNER);
        foodRepository.save(food);
        assertThat(foodRepository.findAll()).hasSize(1);
    }

    @Test
    public void savePersonWithFood() throws Exception {
        food.setFoodType(FoodType.BREAKFAST);
        food.setPerson(person1);
        food2.setFoodType(FoodType.DESSERT);
        food2.setPerson(person1);
        person1.getOrders().put(FoodType.BREAKFAST, food);
        person1.getOrders().put(FoodType.DESSERT, food2);

        personRepository.save(person1);

        assertThat(personRepository.getOne(person1.getId()).getOrders().get(FoodType.DESSERT)).isEqualTo(food2);
    }

    @Test
    public void removePerson() throws Exception {


    }
}
