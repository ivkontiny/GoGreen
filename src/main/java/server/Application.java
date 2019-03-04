package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {


    private static String SESSIONID;


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
