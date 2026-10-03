package hospital_management.project.controllers;

import hospital_management.project.models.Patient;
import hospital_management.project.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public Page<Patient> getAllPatients(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "4") int size){
        System.out.println("fetching the patients details.");
        return patientService.getAllPatients(page,size);
    }
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        System.out.println("Suceesfully created patient details in db.");
        return patientService.createPatient(patient);

    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id){
        System.out.println("Fetching patient by id");
        return patientService.getPatientById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id){
        System.out.println("Deleted patient with id: " + id);
        patientService.deletePatient(id);

    }
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient){
        System.out.println("Updated patient details with id: " + id);
        return patientService.updatePatient(id, patient);

    }
}
