package in.coderarmy;

import in.coderarmy.payment.PaymentService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class CardPayment implements PaymentService {
    @Override
    public void pay(){
        System.out.println("Payment through card");
    }
}
