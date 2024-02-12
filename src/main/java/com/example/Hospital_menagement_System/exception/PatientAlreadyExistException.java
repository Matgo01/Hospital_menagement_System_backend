package com.example.Hospital_menagement_System.exception;

public class PatientAlreadyExistException extends RuntimeException {
    public PatientAlreadyExistException(String s)  {
        super(s);
    }
}
