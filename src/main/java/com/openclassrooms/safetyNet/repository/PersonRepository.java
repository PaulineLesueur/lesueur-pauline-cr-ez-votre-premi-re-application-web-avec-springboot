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

    @Query(value = "SELECT * FROM Persons WHERE last_name = :lastname AND first_name = :firstname", nativeQuery = true)
    Optional<Person> findByLastnameAndFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);

    @Modifying
    @Query(value = "DELETE * FROM Persons WHERE last_name = :lastname AND first_name = :firstname", nativeQuery = true)
    void delete(@Param("lastname") String lastname, @Param("firstname") String firstname);

}
