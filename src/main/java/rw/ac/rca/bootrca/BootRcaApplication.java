package rw.ac.rca.bootrca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BootRcaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootRcaApplication.class, args);
    }

}
