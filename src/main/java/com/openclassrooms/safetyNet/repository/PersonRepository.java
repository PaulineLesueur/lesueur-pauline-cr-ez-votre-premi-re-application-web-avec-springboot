package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Modifying
    @Query(value = "DELETE p FROM Person p WHERE p.lastname = :lastname AND p.firstname = :firstname", nativeQuery = true)
    void delete(@Param("lastname") String lastname, @Param("firstname") String firstname);

}
