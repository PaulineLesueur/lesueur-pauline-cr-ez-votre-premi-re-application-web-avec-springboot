package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.Firestation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Long> {

}
