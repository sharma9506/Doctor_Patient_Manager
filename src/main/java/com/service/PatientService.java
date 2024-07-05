package com.service;


import com.entity.Patient;
import com.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void removePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}

