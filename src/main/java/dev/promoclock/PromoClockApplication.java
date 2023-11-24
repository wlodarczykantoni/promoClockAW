
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

            // pobieranie aktualnej daty i czas
            LocalDateTime teraz = LocalDateTime.now();

            // wypisanie aktualnej dsaty i czasu
            System.out.println("Rok: " + teraz.getYear());
            System.out.println("Miesiąc: " + teraz.getMonthValue());
            System.out.println("Dzień: " + teraz.getDayOfMonth());
            System.out.println("Godzina: " + teraz.getHour());
            System.out.println("Minuta: " + teraz.getMinute());
            System.out.println("Sekunda: " + teraz.getSecond());

            // Pobieranie danych od użytkownika
            Scanner scanner = new Scanner(System.in);

            System.out.print("Podaj rok (większy lub równy aktualnemu): ");
            int rokNext = scanner.nextInt();

            System.out.print("Podaj miesiąc (1-12): ");
            int miesiacNext = scanner.nextInt();

            System.out.print("Podaj dzień (1-31): ");
            int dzienNext = scanner.nextInt();

            System.out.print("Podaj godzinę (0-23): ");
            int godzinaNext = scanner.nextInt();

            System.out.print("Podaj minutę (0-59): ");
            int minutaNext = scanner.nextInt();

            System.out.print("Podaj sekundy (0-59): ");
            int sekundyNext = scanner.nextInt();


            LocalDate podanaData = LocalDate.of(rokNext, miesiacNext, dzienNext);
            LocalTime podanaGodzina = LocalTime.of(godzinaNext, minutaNext, sekundyNext);

            if (podanaData.isBefore(teraz.toLocalDate()) || (podanaData.isEqual(teraz.toLocalDate()) && podanaGodzina.isBefore(teraz.toLocalTime()))) {
                System.out.println("Błąd: Wprowadzona data i godzina muszą być większe lub równe aktualnej.");
                return;
            }

            if (sekundyNext < 0 || sekundyNext > 59 || minutaNext < 0 || minutaNext > 59 || godzinaNext < 0 || godzinaNext > 23 || dzienNext < 1 || dzienNext > 31) {
                System.out.println("Błąd: Niepoprawne wartości dla sekund, minut, godzin lub dni.");
                return;
            }
// liczenie jaka jest roznica czasu
            int roznicaLat = Math.max(0, teraz.getYear() - podanaData.getYear());
            int roznicaMiesiecy = Math.max(0, teraz.getMonthValue() - podanaData.getMonthValue() + 12 * roznicaLat);
            int roznicaDni = Math.max(0, teraz.getDayOfMonth() - podanaData.getDayOfMonth());
            int roznicaGodzin = Math.max(0, teraz.getHour() - godzinaNext);
            int roznicaMinut = Math.max(0, teraz.getMinute() - minutaNext);
            int roznicaSekund = Math.max(0, teraz.getSecond() - sekundyNext);

// wypisanie akutalnej oczekiwanej i czas oczekiwania
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("Aktualna data: " + teraz.format(formatter));
            System.out.println("Oczekiwana data: " + podanaData.atStartOfDay().plusHours(godzinaNext).plusMinutes(minutaNext).plusSeconds(sekundyNext).format(formatter));
            System.out.println("Pozostały czas:");
            System.out.println("  Lata: " + roznicaLat);
            System.out.println("  Miesiące: " + roznicaMiesiecy);
            System.out.println("  Dni: " + roznicaDni);
            System.out.println("  Godziny: " + roznicaGodzin);
            System.out.println("  Minuty: " + roznicaMinut);
            System.out.println("  Sekundy: " + roznicaSekund);


        };
    }
}
