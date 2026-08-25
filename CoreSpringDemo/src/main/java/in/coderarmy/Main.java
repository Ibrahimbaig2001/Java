package in.coderarmy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    OrderService order = context.getBean(OrderService.class);
    order.placeOrder();
    User user = context.getBean(User.class);
        System.out.println(user.getName());
//    PaymentService payment = context.getBean(PaymentService.class);
//    payment.pay();
//        PaymentService service = new PaymentService();
//        OrderService order = new OrderService(notification);
//        OrderService order = new OrderService(service);
//        order.placeOrder();
         // c1 is not an object of student class, but a special type of variable which holds the meta data of student class.

    }
}

