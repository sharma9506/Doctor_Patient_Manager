package com.controller;

import com.entity.Doctor;
import com.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@Validated @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeDoctor(@PathVariable Long id) {
        doctorService.removeDoctor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors(@RequestParam String city, @RequestParam String speciality) {
        return ResponseEntity.ok(doctorService.getDoctorsByCityAndSpeciality(city, speciality));
    }
}

