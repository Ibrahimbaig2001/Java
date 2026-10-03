package hospital_management.project.controllers;

import com.razorpay.Utils;
import hospital_management.project.models.Payment;
import hospital_management.project.service.PaymentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class RazorpayWebhookController {

    @Value("${razorpay.webhook.secret}")
    private String webhookSecret;

    private final PaymentService paymentService;
    public RazorpayWebhookController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public ResponseEntity<String> handlePaymentWebhook(@RequestBody String payload, @RequestHeader("X-Razorpay-Signature") String signature){
        try {
            Utils.verifyWebhookSignature(payload,signature,webhookSecret);
            System.out.println("Razorpay webhook recieved.");
            System.out.println("payload: " + payload);
            JSONObject webhook = new JSONObject(payload);
            String event = webhook.getString("event");
            if("payment.captured".equals(event)){
                JSONObject paymentEntity = webhook.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");
                String paymentId = paymentEntity.getString("id");
                String orderId = paymentEntity.getString("order_id");
                System.out.println("Payment Id: " + paymentId);
                System.out.println("Order Id: " + orderId);
                paymentService.handleSuccessfulPayment(orderId, paymentId);
            }
            return ResponseEntity.ok("Webhook Recieved");
        } catch (Exception e){
            System.out.println("Invalid Razorpay Webhook: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid Webhook");

        }

    }
}
