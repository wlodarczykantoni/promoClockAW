package dev.promoclock;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @GetMapping("/index/{data}")
    public String clock(@PathVariable String data, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDateTime podanyCzas = LocalDateTime.of(LocalDate.parse(data, formatter), LocalTime.MIDNIGHT);
        LocalDateTime teraz = LocalDateTime.now();

        // nadanie wartosc i liczenie ich
        long roznicaLat = teraz.getYear() - podanyCzas.getYear();
        long roznicaMiesiecy = teraz.getMonthValue() - podanyCzas.getMonthValue() + 12 * roznicaLat;
        long roznicaDni = teraz.getDayOfMonth() - podanyCzas.getDayOfMonth();
        long roznicaGodzin = teraz.getHour() - podanyCzas.getHour();
        long roznicaMinut = teraz.getMinute() - podanyCzas.getMinute();
        long roznicaSekund = teraz.getSecond() - podanyCzas.getSecond();

        // aby nie bylo za duzo
        if (roznicaSekund > 0) {
            roznicaMinut++;
            roznicaSekund %= 60;
        }

        if (roznicaMinut > 0) {
            roznicaGodzin++;
            roznicaMinut %= 60;
        }

        /// wrzucanie do html
        model.addAttribute("aktualnaData", teraz.format(formatter));
        model.addAttribute("oczekiwanaData", podanyCzas.format(formatter));
        model.addAttribute("roznicaLat", roznicaLat);
        model.addAttribute("roznicaMiesiecy", roznicaMiesiecy);
        model.addAttribute("roznicaDni", roznicaDni);
        model.addAttribute("roznicaGodzin", roznicaGodzin);
        model.addAttribute("roznicaMinut", roznicaMinut);
        model.addAttribute("roznicaSekund", roznicaSekund);

        /// odpalenie  HTML
        return "index.html";
    }
}
