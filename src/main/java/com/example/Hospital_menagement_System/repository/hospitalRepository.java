package com.example.Hospital_menagement_System.repository;

import com.example.Hospital_menagement_System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface hospitalRepository extends JpaRepository<Patient,Long> {


}
