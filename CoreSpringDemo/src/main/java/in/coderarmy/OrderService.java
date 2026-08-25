package in.coderarmy;

import in.coderarmy.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
//    @Autowired // field injection , but field injection is not recommended, use either setter or constructor injection.
    private PaymentService paymentService;
    @Autowired
    public OrderService(@Qualifier("upiPayment") PaymentService paymentService){
        this.paymentService = paymentService; // Constructor Injection, most recommended approach
    }
//    @Autowired
//    public void setPaymentService(PaymentService paymentService){ // setter method injection
//        this.paymentService = paymentService;
//    }
    public void placeOrder(){
        paymentService.pay();
      System.out.println("Order Placed");
    }
}
