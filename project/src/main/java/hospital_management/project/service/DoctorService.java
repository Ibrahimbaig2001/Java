package hospital_management.project.service;


import hospital_management.project.models.Doctor;
import hospital_management.project.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    public Page<Doctor> getAllDoctors(int page, int size){
        try {
            System.out.println("Fetching all doctors from service");
            Pageable pageable = PageRequest.of(page,size);
            return doctorRepository.findAll(pageable);

        } catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public Doctor getDoctorById(Long id){
        try {
            System.out.println("Getting doctor details of id: " + id);
            Optional<Doctor> doctorRes = doctorRepository.findById(id);
            return doctorRes.orElse(null);
        }
        catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return  null;
        }
    }
    public Doctor createDoctor(Doctor doctor){
        try {
            Doctor doctorRes = doctorRepository.save(doctor);
            System.out.println("Doctor details saved");
            return doctorRes;

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public void deleteDoctor(Long id){
        try {
            doctorRepository.deleteById(id);
            System.out.println("Doctor with id: " + id +"deleted");
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }
    public Doctor updateDoctor(Long id, Doctor updateDoctor){
        try {
            Optional<Doctor> doctorExists = doctorRepository.findById(id);
            if(doctorExists.isPresent()){
                Doctor docRes = doctorExists.get();
                docRes.setDoctorName(updateDoctor.getDoctorName());
                docRes.setAge(updateDoctor.getAge());
                docRes.setDoctorSpecialization(updateDoctor.getDoctorSpecialization());
                doctorRepository.save(docRes);
                System.out.println("Doctor details with id: " + id + "updated successfully");
                return updateDoctor;
            } else {
                System.out.println("Doctor with id: " + id +"not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
}
