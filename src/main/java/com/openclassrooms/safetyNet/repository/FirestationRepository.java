package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.Firestation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {

    @Modifying
    @Query(value= "DELETE FROM Firestations WHERE address = :address", nativeQuery = true)
    void deleteByAddress(@Param("address") String address);

    @Query(value = "SELECT f.station FROM Firestations f WHERE f.address = :address", nativeQuery = true)
    Integer findStationNumberByAddress(@Param("address") String address);
}
