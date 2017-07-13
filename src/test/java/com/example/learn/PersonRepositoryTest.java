package com.example.learn;

import com.example.learn.model.Person;
import com.example.learn.service.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("moje")
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

//    @MockBean
//    private PersonRepository personRepositoryMock;

    private Person person;
    private Person person2;
    private Person personSameButDifferent;

    @Before
    public void init() {
        person = new Person("Yoda", 24, 4.44);
        person2 = new Person("Vader", 24, 8);
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

    @Test
    public void customPersonRepoOperation()  {
        personRepository.save(person);
        personRepository.save(person2);
        assertThat(personRepository.findAll()).hasSize(2);
        assertThat(personRepository.findWithMinimumPowerOf(5L)).hasSize(1);
        assertThat(personRepository.findWithMinimumPowerOf(4L)).hasSize(2);
        assertThat(personRepository.findWithMinimumPowerOf(9L)).isEmpty();
    }

}
