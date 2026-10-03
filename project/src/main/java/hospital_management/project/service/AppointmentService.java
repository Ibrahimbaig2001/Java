package hospital_management.project.service;

import hospital_management.project.models.Appointment;
import hospital_management.project.repository.AppointmentRepository;
import hospital_management.project.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;
    public Page<Appointment> getAllAppointments(int page, int size){
        try {
            System.out.println("Fetching all appointments from service");
            Pageable pageable = PageRequest.of(page,size);
            return appointmentRepository.findAll(pageable);

        } catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public Appointment getAppointmentById(Long id){
        try {
            System.out.println("Getting appointment of id: " + id);
            Optional<Appointment> appointmentRes = appointmentRepository.findById(id);
            return appointmentRes.orElse(null);
        }
        catch (Exception e){
            System.out.println("Error message: " + e.getMessage());
            return  null;
        }
    }
    public Appointment createAppointment(Appointment appointment){
        try {
            System.out.println("Appointment created");
            appointment.setPaymentStatus("PAYMENT_PENDING");
            return appointmentRepository.save(appointment);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
    public void deleteAppointment(Long id){
        try {
            System.out.println("Appointment with id: " + id +"deleted");
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }
    public Appointment updateAppointment(Long id, Appointment updateAppointment){
        try {
            Optional<Appointment> appointmentExistst = appointmentRepository.findById(id);
            if(appointmentExistst.isPresent()){
                Appointment a = appointmentExistst.get();
                a.setPatientId(updateAppointment.getPatientId());
                a.setDoctorId(updateAppointment.getDoctorId());
                a.setDate(updateAppointment.getDate());
                appointmentRepository.save(a);
                System.out.println("Appointment with id: " + id + "updated successfully");
                return a;
            } else{
                System.out.println("Appointment with id: " + id +"not found");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
            return null;
        }
    }
}
