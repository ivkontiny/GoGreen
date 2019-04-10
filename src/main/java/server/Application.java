package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import util.DailyThread;

@SpringBootApplication
public class Application {

    /**
    * Executes the arguments and starts the application.
    * @param args the arguments to be executed.
    */
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        DailyThread thread = new DailyThread();
        thread.start();
    }
}
