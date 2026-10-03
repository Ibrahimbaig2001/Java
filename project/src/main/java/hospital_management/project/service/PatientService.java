package hospital_management.project.service;

import hospital_management.project.models.Patient;
import hospital_management.project.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page, int size){
        try {
            System.out.println("Into Service Layer.");
            Pageable pageable = PageRequest.of(page,size);
            return patientRepository.findAll(pageable);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }

//    public List<Patient> getAllPatients(){
//        try {
//            System.out.println("Into Service Layer.");
//            return patientRepository.findAll();
//
//        } catch (Exception e) {
//            System.out.println("Error message: " + e.getMessage());
//            return null;
//        }
//    }
    public Patient getPatientById(Long id){
        try {
            Optional<Patient> patientRes = patientRepository.findById(id);
            return patientRes.orElse(null);
        } catch(Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public Patient createPatient(Patient patient){
        try {
            System.out.println("Patient created");
            Patient patientRes =patientRepository.save(patient);
            return patientRes;
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
            return null;
        }
    }
    public void deletePatient(Long id){
        try {
            patientRepository.deleteById(id);
            System.out.println("Patient Deleted.");
        }
        catch (Exception e){
            System.out.println("Error Message: " + e.getMessage());
        }
    }
    public Patient updatePatient(Long id, Patient updatedPatient){
        try {
            Optional<Patient> existingPatient = patientRepository.findById(id);
            if(existingPatient.isPresent()){
                Patient p = existingPatient.get();
                p.setName(updatedPatient.getName());
                p.setAge(updatedPatient.getAge());
                p.setGender(updatedPatient.getGender());
                p.setEmail(updatedPatient.getEmail());
                patientRepository.save(p);
                return p;
            }
            else{
                System.out.println("Patient with id: " + id +"not found");
                return null;
            }
        } catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
}
