package dev.promoclock;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@ResponseBody
public class GifController {

    @PostMapping(value = "/generate-gif", produces = MediaType.IMAGE_GIF_VALUE)
    public @ResponseBody byte[] generateGif(@RequestBody DateRequest dateRequest) throws IOException {
        GifGenerator gifGenerator = new GifGenerator();
        LocalDateTime targetDateTime = LocalDateTime.of(dateRequest.getDate(), dateRequest.getTime());
        int duration = 1000; // ms = 1s

        // Pobierz kolor, rozmiar i czcionkę z obiektu DateRequest
        Color timerColor = Color.decode(dateRequest.getTimerColor());
        int timerSize = dateRequest.getTimerSize();
        String timerFont = dateRequest.getTimerFont();

        return gifGenerator.generateCountdownGif(String.valueOf(targetDateTime), duration, timerColor, timerSize, timerFont);
    }


    static class DateRequest {
        private LocalDate date;
        private LocalTime time;
        private String timerColor;
        private int timerSize;
        private String timerFont;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public LocalTime getTime() {
            return time;
        }

        public void setTime(LocalTime time) {
            this.time = time;
        }

        public String getTimerColor() {
            return timerColor;
        }

        public void setTimerColor(String timerColor) {
            this.timerColor = timerColor;
        }

        public int getTimerSize() {
            return timerSize;
        }

        public void setTimerSize(int timerSize) {
            this.timerSize = timerSize;
        }

        public String getTimerFont() {
            return timerFont;
        }

        public void setTimerFont(String timerFont) {
            this.timerFont = timerFont;
        }
    }
}
