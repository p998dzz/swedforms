package lt.swedforms;

import lt.swedforms.Entities.Registration;
import lt.swedforms.Entities.User;
import lt.swedforms.repositories.RegistrationRepository;
import lt.swedforms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class AppApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationRepository registrationRepository;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        registrationRepository.deleteAll();
        // save a couple of customers
        User user = new User("test@test.lt", "test");
        userRepository.save(user);

        registrationRepository.save(new Registration(userRepository.findByEmail("test@test.lt"),new Date(),"","","","","",""));
        // fetch all customers
        // fetch an individual customer

        System.out.println(registrationRepository.findByUser(user).size());
    }
}
