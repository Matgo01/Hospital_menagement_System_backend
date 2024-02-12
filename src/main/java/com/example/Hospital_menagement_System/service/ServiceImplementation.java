package com.example.Hospital_menagement_System.service;

import com.example.Hospital_menagement_System.exception.PatientAlreadyExistException;
import com.example.Hospital_menagement_System.exception.PatientNotFoundException;
import com.example.Hospital_menagement_System.model.Patient;
import com.example.Hospital_menagement_System.repository.hospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceImplementation implements ServiceInterface{

    private final hospitalRepository  hospitalRepository;
    @Override
    public Patient addPatient(Patient patient) {
        if(patientAlreadyExist(patient.getId())){
            throw  new PatientAlreadyExistException(patient.getId()+"patient already exist");
        }
        return hospitalRepository.save(patient);
    }

    @Override
    public List<Patient> getPatient() {
        return hospitalRepository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient, Long id) {
        return hospitalRepository.findById(id).map(patient1 -> {
            patient1.setFirstName(patient.getFirstName());
            patient1.setLastName(patient.getLastName());
            patient1.setGender(patient.getGender());
            patient1.setAge(patient.getAge());
            patient1.setResidence(patient.getResidence());
            return hospitalRepository.save(patient1);
        }).orElseThrow(() -> new PatientNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Patient getPatientById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(()->new PatientNotFoundException("sorry patient not found"+id));
    }

    @Override
    public void deletePatient(Long id) {
        if (!hospitalRepository.existsById(id)) {
            throw new PatientNotFoundException("Sorry, patient not found");
        } else {
            hospitalRepository.deleteById(id);
        }
    }
    private boolean patientAlreadyExist(Long id) {
        return hospitalRepository.findById(id).isPresent();
    }
}
