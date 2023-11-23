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
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

@SpringBootApplication
public class PromoClockApplication extends User {

    private static final Logger log = LoggerFactory.getLogger(PromoClockApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(PromoClockApplication.class, args);

    }

    // Spring to odpali po starcie aplikacji
    @Bean
    public CommandLineRunner initDemoDb(dev.promoclock.user.UserRepository userRepository) {
        return (args) -> {
            User admin = new User("admin", LocalDate.now());
            User user1 = new User("Antek", LocalDate.of(2023, 11, 10));
            User user2 = new User("randomUser", LocalDate.of(2023, 5, 5));

            userRepository.save(admin);
            userRepository.save(user1);
            userRepository.save(user2);

            // find all users
            log.info("findAll(), expect 3 users");
            log.info("-------------------------------");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
            }
            log.info("\n");


            System.out.print("tu bedzie licznik:");
            //zabrane z jakiegos kursu
            Date akutalnaGodzina = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
            System.out.println(simpleDateFormat.format(akutalnaGodzina));


            // Pobieranie date i czas
            Calendar now = Calendar.getInstance();

            // Pobieranie wsszystkiego i nadanie wartosci
            int rok = now.get(Calendar.YEAR);
            int miesiac = now.get(Calendar.MONTH) + 1; // dodajemu 1 bo miesace sa od zera
            int dzien = now.get(Calendar.DAY_OF_MONTH);
            int godzina = now.get(Calendar.HOUR_OF_DAY);
            int minuta = now.get(Calendar.MINUTE);

            // wypisuejmy wszystkie
            System.out.println("Rok: " + rok);
            System.out.println("Miesiąc: " + miesiac);
            System.out.println("Dzień: " + dzien);
            System.out.println("Godzina: " + godzina);
            System.out.println("Minuta: " + minuta);
//sprawdzamy czy dziala
            /*
int rokNext = 10;
int rokWant = rok + rokNext;
System.out.println("rok ktory chcemy" + rokWant);
*/

            Scanner scanner = new Scanner(System.in);



                System.out.print("Podaj pierwszą liczbę: ");
                int liczba1 = scanner.nextInt();

                System.out.print("Podaj drugą liczbę: ");
                int liczba2 = scanner.nextInt();

                System.out.print("Wybierz operację:\n"
                        + "1 - Dodawanie\n"
                        + "2 - Odejmowanie\n"
                        + "3 - Mnożenie\n"
                        + "4 - Dzielenie\n");


        };
    }
}

