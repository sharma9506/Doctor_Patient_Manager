package com.service;


import com.entity.Doctor;
import com.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void removeDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> getDoctorsByCityAndSpeciality(String city, String speciality) {
        return doctorRepository.findByCityAndSpeciality(city, speciality);
    }
}
