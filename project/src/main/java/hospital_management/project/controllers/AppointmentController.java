package hospital_management.project.controllers;

import hospital_management.project.models.Appointment;
import hospital_management.project.models.Doctor;
import hospital_management.project.models.Patient;
import hospital_management.project.repository.DoctorRepository;
import hospital_management.project.repository.PatientRepository;
import hospital_management.project.service.AppointmentService;
import hospital_management.project.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private WebhookService webhookService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @GetMapping
    public Page<Appointment> getAllAppointments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size){
        System.out.println("fetching the patients details.");
        return appointmentService.getAllAppointments(page,size);
    }
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointmentReq){
        Appointment appointment = appointmentService.createAppointment(appointmentReq);
        System.out.println("Successfully created patient appointment details in db.");
        Map<String, Object> payload = new HashMap<>();
        payload.put("appointmentId",appointment.getId());
        payload.put("patientId",appointment.getPatientId());
        payload.put("doctorId", appointment.getDoctorId());
        Doctor doctor = doctorRepository.findById(appointment.getDoctorId()).orElseThrow(() -> new RuntimeException("Patient Not Found."));
        payload.put("doctorName", doctor.getDoctorName());
        Patient patient = patientRepository.findById(appointment.getPatientId()).orElseThrow(() -> new RuntimeException("Patient Not Found."));
        payload.put("patientName", patient.getName());
        payload.put("patientEmail",patient.getEmail());
        payload.put("date", appointment.getDate());
        String webhookUrl = "http://localhost:8080/webhook/appointment";
        webhookService.sendWebhook(webhookUrl,payload);
        return appointment;

    }
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        System.out.println("Fetching patient by id");
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id){
        appointmentService.deleteAppointment(id);
        System.out.println("Deleted patient appointment with id: " + id);

    }
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        System.out.println("Updated patient appointment with id: " + id);
        return appointmentService.updateAppointment(id, appointment);

    }
}
