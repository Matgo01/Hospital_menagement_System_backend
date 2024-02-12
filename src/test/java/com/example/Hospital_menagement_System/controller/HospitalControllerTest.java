package com.example.Hospital_menagement_System.controller;

import com.example.Hospital_menagement_System.model.Patient;
import com.example.Hospital_menagement_System.service.ServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(HospitalController.class)
class HospitalControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceImplementation serviceImplementation;

    Patient patient0;

    Patient patient1;

    List<Patient> patientList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        patient0=new Patient(1L,"matteo","gorla","maschio",22,"casorezzo");
        patient1=new Patient(2L,"andrea","gorla","maschio",20,"casorezzo");
        patientList.add(patient0);
        patientList.add(patient1);
    }

    @Test
    void getAllPatient() throws Exception {
        when(serviceImplementation.getPatient()).thenReturn(patientList);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].firstName").value("matteo"))// Verifica il contenuto della risposta
                .andExpect(jsonPath("$[1].firstName").value("andrea"));
    }

    @Test
    void addPatient() throws Exception {

        when(serviceImplementation.addPatient(any(Patient.class))).thenReturn(patient0);


        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"firstName\":\"matteo\",\"lastName\":\"gorla\",\"gender\":\"maschio\",\"age\":\"22\",\"residence\":\"casorezzo\"}"))
                .andExpect(status().isOk()) // Verifica lo status della risposta
                .andExpect(jsonPath("$.firstName").value("matteo"));

    }

    @Test
    void updatePatient() throws Exception {
        Patient updatedPatient= new Patient(3L, "alessandro", "gorla", "maschio",58,"casorezzo");
        when(serviceImplementation.updatePatient(any(Patient.class), anyLong())).thenReturn(updatedPatient);


        mockMvc.perform(put("/patients/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"firstName\":\"alessandro\",\"lastName\":\"gorla\",\"gender\":\"maschio\",\"eta\":\"58\",\"residence\":\"casorezzo\"}"))
                .andExpect(status().isOk()) // Verifica lo status della risposta
                .andExpect(jsonPath("$.firstName").value("alessandro"));
    }

    @Test
    void deletePatient() throws Exception {
        mockMvc.perform(delete("/patients/delete/1"))
                .andExpect(status().isOk());
        verify(serviceImplementation,times(1)).deletePatient(1L);
    }

    @Test
    void getPatientById() throws Exception {
        when(serviceImplementation.getPatientById(1L)).thenReturn(patient0);

        mockMvc.perform(get("/patients/patient/1"))
                .andExpect(status().isOk()) // Verifica lo status della risposta
                .andExpect(jsonPath("$.firstName").value("matteo"));
    }
}