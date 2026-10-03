package hospital_management.project.controllers;

import hospital_management.project.models.Payment;
import hospital_management.project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) throws Exception{
        return  paymentService.createPayment(payment);
    }
    @PostMapping("/test-success")
    public String testSuccessfulPayment(
            @RequestParam String orderId,
            @RequestParam String paymentId) {

        paymentService.handleSuccessfulPayment(orderId, paymentId);

        return "Payment successfully processed";
    }

}
