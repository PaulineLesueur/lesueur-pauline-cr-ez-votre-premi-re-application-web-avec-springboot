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
    @Query(value = "DELETE f FROM Firestation f WHERE f.station = :station", nativeQuery = true)
    void deleteByStationNumber(@Param("station") Integer station);

    @Modifying
    @Query(value= "DELETE f FROM Firestation f WHERE f.adress = :adress", nativeQuery = true)
    void deleteByAdress(@Param("adress") String adress);
}
