package hospital_management.project.controllers;

import hospital_management.project.models.Doctor;
import hospital_management.project.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public Page<Doctor> getAllDoctors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size ){
        System.out.println("Fetching all doctor details");
        return doctorService.getAllDoctors(page,size);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor){
        System.out.println("Successfully listed doctor details.");
        return doctorService.createDoctor(doctor);
    }
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id){
        System.out.println("Returning docror with the id: "+ id);
        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorById(@PathVariable Long id){
        System.out.println("Successfully deleted doctor details with id: " + id);
    }

    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor){
        System.out.println("Successfully updated doctor details of id: " + id);
        return doctorService.updateDoctor(id, doctor);

    }
}
