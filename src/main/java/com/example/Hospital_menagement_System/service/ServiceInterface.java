package com.example.Hospital_menagement_System.service;

import com.example.Hospital_menagement_System.model.Patient;

import java.util.List;

public interface ServiceInterface {

    Patient addPatient(Patient patient);

    List<Patient> getPatient();

    Patient updatePatient(Patient patient, Long id);

    Patient getPatientById(Long id);

    void deletePatient(Long id);
}
