package server;

import client.ConnectAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import util.AddSolarPoints;

@SpringBootApplication
public class Application {

    /**
    * Executes the arguments and starts the application.
    * @param args the arguments to be executed.
    */
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        AddSolarPoints.addPoints();
        ConnectAccount.resetHeating();
    }
}
