package com.example.learn;

import com.example.learn.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StreamsTest {
    private List<Person> persons = Arrays.asList(
            new Person("Yoda", 24, 4.44),
            new Person("R2D2", 74, 8.0),
            new Person("Sith", 44, 2.0),
            new Person("Vader", 55, 9.5),
            new Person("Leia", 55, 7.0),
            new Person("Luke", 88, 7.0));
    private Person vader;
    private Person luke;

//    private final Logger log = LoggerFactory.getLogger(this.getClass());

    //@Before - before each test, @BeforeClass - once before all
    @Before
    public void before() {
        vader = new Person("Vader", 55, 9.5);
        luke = new Person("Luke", 88, 7.0);
    }

    @Test
    public void collectJoiningTest() {
        log.info("start");
        persons.sort(Comparator.comparing(Person::getAge));
        String namesString = Stream.of(vader, luke)
                .map(p -> p.getName().toUpperCase())
                .collect(Collectors.joining(", "));

        assertThat(namesString).isEqualTo("Vader, Luke".toUpperCase());
    }

    @Test
    public void filterSortToList() {
        List<Person> strongest = persons.stream()
                .filter(p -> p.getPower() >= 5)
                .sorted(Comparator.comparingDouble(Person::getPower).reversed())
                .collect(Collectors.toList());
        assertThat(strongest.get(0).getName()).isEqualToIgnoringCase(vader.getName());
    }

    @Test
    public void groupByPower() {
        Map<Double, List<Person>> personsByPower = persons.stream()
                .collect(Collectors.groupingBy(Person::getPower));

        assertThat(personsByPower.get(7.0)).hasSize(2);
        assertThat(personsByPower.get(7.0).toString()).contains("Luke", "Leia");
    }

    @Test
    public void summarizePower() {
        IntSummaryStatistics statistics = persons.stream().collect(Collectors.summarizingInt(Person::getAge));
        assertThat(statistics.getMax()).isEqualTo(88);
        assertThat(statistics.getMin()).isEqualTo(24);
    }

    @Test(expected = NullPointerException.class)
    public void shouldValidateNull() {
        Integer val = null;
        assertThat(val).isNull();
        assertThat(Objects.isNull(val)).isTrue();
        Objects.requireNonNull(val);
    }
}
