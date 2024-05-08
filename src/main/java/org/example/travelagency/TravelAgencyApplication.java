package org.example.travelagency;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.travelagency.model.User;
import org.example.travelagency.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import java.util.Random;

@AllArgsConstructor
@SpringBootApplication
public class TravelAgencyApplication {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FindByIndexNameSessionRepository<? extends Session> sessionRepository;

    @PostConstruct
    public void initAdmin(){
        User admin = userRepository.findByUsername("admin").orElse(new User("admin", "", User.Role.ADMIN));
        Random random = new Random();
        String dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = random.ints(0, dict.length())
                .limit(40)
                .collect(StringBuilder::new, (str, ind)-> str.append(dict.charAt(ind)), StringBuilder::append)
                .toString();

        admin.setPassword(passwordEncoder.encode(password));
        userRepository.save(admin);
        System.out.println("----------------------------------------------------------------\n\nCurrent admin password: " +
                password+"\n\n----------------------------------------------------------------");
        for (Session i: sessionRepository.findByPrincipalName("admin").values()){
            sessionRepository.deleteById(i.getId());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

}
