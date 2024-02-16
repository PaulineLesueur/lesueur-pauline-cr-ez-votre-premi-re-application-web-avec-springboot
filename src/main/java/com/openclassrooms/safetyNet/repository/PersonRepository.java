package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
