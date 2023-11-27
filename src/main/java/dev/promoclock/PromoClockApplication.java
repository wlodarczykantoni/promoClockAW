
package dev.promoclock;

import dev.promoclock.dev.promoclock.user.User;
//import dev.promoclock.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;
@SpringBootApplication
public class PromoClockApplication extends User {

    private static final Logger log = LoggerFactory.getLogger(PromoClockApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PromoClockApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDemoDb(dev.promoclock.user.UserRepository userRepository) {
        return (args) -> {
            User admin = new User("admin", LocalDate.now());
            User user1 = new User("Antek", LocalDate.of(2023, 11, 10));
            User user2 = new User("randomUser", LocalDate.of(2023, 5, 5));

            userRepository.save(admin);
            userRepository.save(user1);
            userRepository.save(user2);

            log.info("findAll(), expect 3 users");
            log.info("-------------------------------");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }
            log.info("\n");
        };

    }
}