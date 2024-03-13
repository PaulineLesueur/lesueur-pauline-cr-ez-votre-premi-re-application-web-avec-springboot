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

    @Query(value = "SELECT p.email FROM Persons p WHERE p.city = :city", nativeQuery = true)
    Optional<Iterable<String>> findCommunityEmail(@Param("city") String city);

    @Query(value = "SELECT p.phone FROM Persons p, Firestations f WHERE f.station = :station and p.address = f.address", nativeQuery = true)
    Optional<Iterable<String>> findPhoneByStationNumber(@Param("station") Integer station);

    @Query(value = "SELECT * FROM Persons WHERE last_name = :lastname AND first_name = :firstname", nativeQuery = true)
    Optional<Person> findByLastnameAndFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname);

    @Modifying
    @Query(value = "DELETE FROM Persons WHERE last_name = :lastname AND first_name = :firstname", nativeQuery = true)
    void delete(@Param("lastname") String lastname, @Param("firstname") String firstname);

}
