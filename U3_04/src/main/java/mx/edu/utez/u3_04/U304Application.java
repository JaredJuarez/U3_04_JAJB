package mx.edu.utez.u3_04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class U304Application {

    public static void main(String[] args) {
        SpringApplication.run(U304Application.class, args);
    }

}
