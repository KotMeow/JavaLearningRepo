package com.example.learn;

import com.example.learn.model.Greeting;
import com.example.learn.model.Person;
import com.example.learn.service.PersonRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository repository;

    private Person person1;
    private Person person2;
    private Greeting greeting;

    @Before
    public void init() {
        person1 = new Person("Yoda", 24, 4.44);
        person2 = new Person("Vader", 24, 4.44);
        greeting = new Greeting("Kamil", 55);
    }

    @Test
    public void shouldReturnHello() throws Exception {
        this.mockMvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string("hello"));
    }

    @Test
    public void shouldReturnArrayOfPersons() throws Exception {

        when(repository.findAll()).thenReturn(Arrays.asList(person1, person2));

        this.mockMvc.perform(get("/data")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Yoda")))
                .andExpect(jsonPath("$[1].name", is("Vader")))
                .andExpect(jsonPath("$..power", everyItem(is(4.44))));
    }

    @Test
    public void shouldReturnOnePerson() throws Exception {

        when(repository.findOne(1L)).thenReturn(person1);
        when(repository.findOne(2L)).thenReturn(person2);

        this.mockMvc.perform(get("/data/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Yoda")));

        this.mockMvc.perform(get("/data/2")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Vader")));

    }

    @Test
    public void shouldSendGreeting() throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(greeting);
        this.mockMvc.perform(post("/send").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(greeting.getName())))
                .andExpect(jsonPath("$.age", is(greeting.getAge() + 10)));

    }
}
