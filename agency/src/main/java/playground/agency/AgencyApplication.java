package playground.agency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"playground.agency.*"})
//@EnableConfigurationProperties({AuthProperties.class})
//@EnableCaching
//@EnableScheduling
public class AgencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgencyApplication.class, args);
    }
}
