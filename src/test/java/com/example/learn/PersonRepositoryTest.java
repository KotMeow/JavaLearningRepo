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
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

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

    @Before
    public void init() {
        food = new Food("Cherry Pie", 250);
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
        personRepository.save(person2);
        person1.setFood(food);
        food.setPerson(person1);
        personRepository.save(person1);

        assertThat(personRepository.findAll()).hasSize(2);
        assertThat(foodRepository.findAll()).hasSize(1);
        assertThat(foodRepository.findOne(food.getId()).getPerson()).isEqualToIgnoringGivenFields(person1, "food");
        assertThat(personRepository.findOne(person1.getId()).getFood()).isEqualToIgnoringGivenFields(food, "person");
    }

    @Test
    public void removePerson() throws Exception {
        person1.setFood(food);
        personRepository.save(person1);

        assertThat(personRepository.findOne(person1.getId()).getFood()).isEqualTo(food);

        personRepository.delete(person1.getId());

        assertThat(personRepository.findAll()).isEmpty();
        assertThat(foodRepository.findAll()).isEmpty();

    }
}
