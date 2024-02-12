package com.example.Hospital_menagement_System.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String s) {
        super(s);
    }
}
