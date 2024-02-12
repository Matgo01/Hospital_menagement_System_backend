package com.example.Hospital_menagement_System.controller;

import com.example.Hospital_menagement_System.model.Patient;
import com.example.Hospital_menagement_System.service.ServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhsot:3000")
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class HospitalController {
    private final ServiceImplementation serviceImplementation;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        return new ResponseEntity<>(serviceImplementation.getPatient(), HttpStatus.FOUND);
    }
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient){
        return serviceImplementation.addPatient(patient);
    }
    @PutMapping("/update/{id}")
    public Patient updatePatient(@RequestBody Patient patient, @PathVariable Long id){
        return serviceImplementation.updatePatient(patient,id);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable Long id){
        serviceImplementation.deletePatient(id);
    }
    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable Long id){
        return serviceImplementation.getPatientById(id);

    }
}
