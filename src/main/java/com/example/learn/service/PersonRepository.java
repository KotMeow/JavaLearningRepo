package com.example.learn.service;

import com.example.learn.model.Person;
import com.example.learn.model.projections.PersonDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RepositoryRestResource(collectionResourceRel = "people", excerptProjection = PersonDetails.class)
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(@Param("name") String name);
}
