package in.strikes.SpringBootDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringBootDemoApplication.class, args);
		OrderService order = context.getBean(OrderService.class);
		order.placeOrder();
		}
//		@Bean
//	public UserService getUserServiceBean(){
//		return new UserService();
//	}
//	@Bean
//	public JsonParser getJsonParserBean(){
//		return new BasicJsonParser();
//	} automatically beans are not created for external library files, so we declare like this, to make spring boot handle object in the IOC Container.


}
