package com.controller;
import com.entity.Doctor;
import com.entity.Patient;
import com.service.DoctorService;
import com.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Patient> addPatient(@Validated @RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.addPatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePatient(@PathVariable Long id) {
        patientService.removePatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/suggest-doctors/{id}")
    public ResponseEntity<?> suggestDoctors(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return ResponseEntity.badRequest().body("Invalid patient ID");
        }

        String symptom = patient.getSymptom();
        String city = patient.getCity();

        String speciality;
        switch (symptom) {
            case "Arthritis":
            case "Back Pain":
            case "Tissue injuries":
                speciality = "Orthopaedic";
                break;
            case "Dysmenorrhea":
                speciality = "Gynecology";
                break;
            case "Skin infection":
            case "Skin burn":
                speciality = "Dermatology";
                break;
            case "Ear pain":
                speciality = "ENT";
                break;
            default:
                return ResponseEntity.badRequest().body("Unknown symptom");
        }

        List<Doctor> doctors = doctorService.getDoctorsByCityAndSpeciality(city, speciality);
        if (doctors.isEmpty()) {
            if (!city.equals("Delhi") && !city.equals("Noida") && !city.equals("Faridabad")) {
                return ResponseEntity.ok("We are still waiting to expand to your location");
            } else {
                return ResponseEntity.ok("There isn’t any doctor present at your location for your symptom");
            }
        }

        return ResponseEntity.ok(doctors);
    }
}
