package com.openclassrooms.safetyNet.repository;

import com.openclassrooms.safetyNet.model.Medicalrecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {
}
