package in.coderarmy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("in.coderarmy")
public class AppConfig {

    @Bean
    public User createUser(){
        return new User("Aditya",23);
    }

}
