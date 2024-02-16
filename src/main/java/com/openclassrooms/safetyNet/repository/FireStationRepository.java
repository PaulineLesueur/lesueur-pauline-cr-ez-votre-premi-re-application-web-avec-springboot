package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.FireStation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepository extends CrudRepository<FireStation, Long> {

}
