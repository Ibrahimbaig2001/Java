package hospital_management.project.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import hospital_management.project.models.Appointment;
import hospital_management.project.models.Payment;
import hospital_management.project.repository.AppointmentRepository;
import hospital_management.project.repository.PaymentRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillService billService;
    @Value("${razorpay.key.id}")
    private String razorpayKeyId;
    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;
    public PaymentService(PaymentRepository paymentRepository, AppointmentRepository appointmentRepository, BillService billService)
    {
        this.paymentRepository = paymentRepository;
        this.appointmentRepository = appointmentRepository;
        this.billService = billService;
    }
    public Payment createPayment(Payment payment) throws Exception{
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId,razorpayKeySecret);

        int amountInPaise = (int) (payment.getAmount() * 100);
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amountInPaise);
        orderRequest.put("currency","INR");
        Order razorpayOrder = razorpayClient.orders.create(orderRequest);
        String orderId = razorpayOrder.get("id");
        payment.setOrderId(orderId);
        payment.setStatus("PAYMENT_PENDING");
        return paymentRepository.save(payment);
    }
    public void handleSuccessfulPayment(String orderId, String paymentId){
        Payment payment = paymentRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException("Payment Not Found"));
        if("SUCCESS".equals(payment.getStatus())){
            System.out.println("Payment Already Processed.");
            return;
        }
        payment.setStatus("SUCCESS");
        payment.setPaymentId(paymentId);
        paymentRepository.save(payment);

        Appointment appointment = appointmentRepository.findById(payment.getAppointmentId()).orElseThrow(() -> new RuntimeException("Appointment not Found."));
        appointment.setPaymentStatus("CONFIRMED");
        appointmentRepository.save(appointment);

        billService.generateBill(appointment.getPatientId(), payment.getAmount());
        System.out.println("Payment Successful, Bill generated.");

    }
}
