package algo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Created by AzatYusupov on 19.12.2017.
 */
@SpringBootApplication
@EnableAsync
public class Application {

    @PostConstruct
    void starter() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    public static void main(String []args) {

        SpringApplication.run(Application.class, args);
    }
}
