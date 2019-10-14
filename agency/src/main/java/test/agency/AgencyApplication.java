package test.agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"test.agency.*"})
//@EnableConfigurationProperties({AuthProperties.class})
//@EnableCaching
//@EnableScheduling
public class AgencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgencyApplication.class, args);
    }
}
