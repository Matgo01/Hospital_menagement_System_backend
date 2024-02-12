package com.example.Hospital_menagement_System.service;

import com.example.Hospital_menagement_System.model.Patient;
import com.example.Hospital_menagement_System.repository.hospitalRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
class ServiceImplementationTest {
    @Mock
    private hospitalRepository hospitalRepository;

    private ServiceImplementation serviceImplementation;

    private AutoCloseable autoCloseable;

    private Patient patient;



    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        serviceImplementation=new ServiceImplementation(hospitalRepository);
        patient=new Patient(1L,"matteo","gorla","maschio",22,"casorezzo");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void addPatient() {
        mock(Patient.class);
        mock(hospitalRepository.class);
        when(hospitalRepository.findById(patient.getId())).thenReturn(Optional.empty());
        when(hospitalRepository.save(patient)).thenReturn(patient);


       Patient addedStudent = serviceImplementation.addPatient(patient);
        assertThat(addedStudent).isEqualTo(patient);
    }

    @Test
    void getPatient() {
        mock(Patient.class);
        mock(hospitalRepository.class);
        List<Patient> patientList=List.of(patient);
        when(hospitalRepository.findAll()).thenReturn(patientList);

        List<Patient> retrievedPatients=serviceImplementation.getPatient();
        assertThat(retrievedPatients).isEqualTo(patientList);
    }

    @Test
    void updatePatient() {
        mock(Patient.class);
        mock(hospitalRepository.class);
        when(hospitalRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        when(hospitalRepository.save(patient)).thenReturn(patient);
        Patient updatedPatient=serviceImplementation.updatePatient(patient,patient.getId());
        assertThat(updatedPatient).isEqualTo(patient);
    }

    @Test
    void getPatientById() {
        mock(Patient.class);
        mock(hospitalRepository.class);
        when(hospitalRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
        Patient retrievedPatient=serviceImplementation.getPatientById(patient.getId());
        assertThat(retrievedPatient).isEqualTo(patient);
    }

    @Test
    void deletePatient() {
        mock(Patient.class);
        mock(hospitalRepository.class);
        when(hospitalRepository.existsById(patient.getId())).thenReturn(true);
        serviceImplementation.deletePatient(patient.getId());
        verify(hospitalRepository,times(1)).deleteById(patient.getId());
    }
}