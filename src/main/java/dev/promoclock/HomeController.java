package dev.promoclock;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/welcome/{name}")
    public String name(@PathVariable("name") String name, Model model) {
        model.addAttribute("imie", name);
        return "index";
    }

    @GetMapping(value = "/clock")
    public String clock(@RequestParam String data, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime podanyCzas = LocalDateTime.of(LocalDate.parse(data, formatter), LocalTime.MIDNIGHT);
        LocalDateTime teraz = LocalDateTime.now();

        // liczenie roznicy
        long roznicaLat = teraz.getYear() - podanyCzas.getYear();
        long roznicaMiesiecy = teraz.getMonthValue() - podanyCzas.getMonthValue() + 12 * roznicaLat;
        long roznicaDni = teraz.getDayOfMonth() - podanyCzas.getDayOfMonth();
        long roznicaGodzin = teraz.getHour() - podanyCzas.getHour();
        long roznicaMinut = teraz.getMinute() - podanyCzas.getMinute();
        long roznicaSekund = teraz.getSecond() - podanyCzas.getSecond();

        if (roznicaSekund > 0) {
            roznicaMinut++;
            roznicaSekund %= 60;
        }

        if (roznicaMinut > 0) {
            roznicaGodzin++;
            roznicaMinut %= 60;
        }

        // wysylanie danych
        model.addAttribute("aktualnaData", teraz.format(formatter));
        model.addAttribute("oczekiwanaData", podanyCzas.format(formatter));
        model.addAttribute("roznicaLat", roznicaLat);
        model.addAttribute("roznicaMiesiecy", roznicaMiesiecy);
        model.addAttribute("roznicaDni", roznicaDni);
        model.addAttribute("roznicaGodzin", roznicaGodzin);
        model.addAttribute("roznicaMinut", roznicaMinut);
        model.addAttribute("roznicaSekund", roznicaSekund);

        return "index";
    }

    @PostMapping(value = "/clock")
    @ResponseBody
    public ClockResponse clock(@RequestBody ClockResponse clockResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime podanyCzas = null;
        LocalDateTime teraz = LocalDateTime.now();

        try {
            podanyCzas = LocalDateTime.of(LocalDate.parse(clockResponse.getCurrentDateTime(), formatter), LocalTime.MIDNIGHT);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        if (podanyCzas != null) {
            // oblicznie roznicy
            long roznicaLat = teraz.getYear() - podanyCzas.getYear();
            long roznicaMiesiecy = teraz.getMonthValue() - podanyCzas.getMonthValue() + 12 * roznicaLat;
            long roznicaDni = teraz.getDayOfMonth() - podanyCzas.getDayOfMonth();
            long roznicaGodzin = teraz.getHour() - podanyCzas.getHour();
            long roznicaMinut = teraz.getMinute() - podanyCzas.getMinute();
            long roznicaSekund = teraz.getSecond() - podanyCzas.getSecond();

            // odpowiedz z serwera
            ClockResponse response = new ClockResponse(
                    teraz.format(formatter),
                    podanyCzas.format(formatter),
                    roznicaLat,
                    roznicaMiesiecy,
                    roznicaDni,
                    roznicaGodzin,
                    roznicaMinut,
                    roznicaSekund
            );

            return response;
        }
        return clockResponse;
    }

/*
    @GetMapping(value = "/welcome/{name}")
    public String name(@PathVariable("name") String name, Model model) {
        model.addAttribute("imie", name);
        return "index";
    }*/
}
