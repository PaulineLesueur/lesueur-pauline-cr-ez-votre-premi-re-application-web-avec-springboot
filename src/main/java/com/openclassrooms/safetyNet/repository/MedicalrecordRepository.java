package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {

    @Modifying
    @Query(value = "DELETE m FROM Medicalrecord WHERE m.lastname = :lastname AND m.firstname = :firstname", nativeQuery = true)
    void delete(@Param("lastname") String lastname, @Param("firstname") String firstname);

}
