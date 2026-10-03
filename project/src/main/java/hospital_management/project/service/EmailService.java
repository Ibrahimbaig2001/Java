package hospital_management.project.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    public void sendAppointmentEmail(String patientEmail, String patientName,String doctorName,String appointmentDate){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(patientEmail);
        message.setSubject("Appointment Confirmed");
        message.setText(
                "Dear " + patientName + ",\n\n" +
                        "Your appointment has been successfully scheduled with\n\n" +
                        "Doctor: " + doctorName + "\n" +
                        "Date: " + appointmentDate + "\n\n" +
                        "Thank you for choosing our hospital.\n\n"
        );
        mailSender.send(message);

        System.out.println("Appointment email sent to: " + patientEmail);

    }


}
