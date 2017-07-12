package com.example.learn;

import com.example.learn.model.Person;
import com.example.learn.service.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    private Person person;
    private Person personSameButDifferent;

    @Before
    public void init() {
        person = new Person("Yoda", 24, 4.44);
        personSameButDifferent = new Person("Yoda", 24, 4.44);
    }

    @Test
    public void savePerson() {
        personRepository.save(person);
        assertThat(personRepository.findOne(person.getId())).isEqualTo(person);
        assertThat(personRepository.findOne(person.getId())).isEqualToIgnoringGivenFields(personSameButDifferent, "id");
    }

    @Test
    public void findByName() {
        personRepository.save(person);
        List<Person> found = personRepository.findByName("Yoda");
        assertThat(found).isNotEmpty();
        assertThat(found).contains(person);
    }

}
