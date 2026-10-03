package hospital_management.project.controllers;

import hospital_management.project.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final EmailService emailService;
    public WebhookController(EmailService emailService){
        this.emailService = emailService;
    }
    @PostMapping
    public ResponseEntity<String> recieveWebhook(@RequestBody Map<String, Object> payload){
        System.out.println("Webhook received!");
        System.out.println("Payload: " + payload);

        return ResponseEntity.ok("Webhook received and processed successfully");
    }
    @PostMapping("/appointment")
    public ResponseEntity<String> recieveAppointmentWebhook(@RequestBody Map<String,Object> payload){
        System.out.println("Appointment webhook received");
        System.out.println(payload);

        String patientEmail =
                (String) payload.get("patientEmail");

        String patientName =
                (String) payload.get("patientName");

        String doctorName =
                (String) payload.get("doctorName");

        String date =
                (String) payload.get("date");

        emailService.sendAppointmentEmail(
                patientEmail,
                patientName,
                doctorName,
                date
        );

        return ResponseEntity.ok(
                "Appointment webhook processed successfully"
        );
    }

}
