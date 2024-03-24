package dev.promoclock;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.madgag.gif.fmsware.AnimatedGifEncoder;

public class GifGenerator {

    // Metoda generująca plik GIF na podstawie tekstu, koloru, rozmiaru i czcionki
    public byte[] generateCountdownGif(String text, int frameDuration,Color timerColor, int timerSize, String timerFont ) throws IOException {
        List<String> frames = new ArrayList<>();
        frames.add(text);
        return generateGifFrames(frames, timerColor, timerSize, timerFont, frameDuration);
    }

    // Metoda generująca plik GIF na podstawie listy ramek, koloru, rozmiaru i czcionki
    private byte[] generateGifFrames(List<String> frames, Color timerColor, int timerSize, String timerFont, int frameDuration) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
        gifEncoder.start(outputStream);
        gifEncoder.setRepeat(0); // Powtarzaj w nieskończoność
        gifEncoder.setDelay(frameDuration); // Opóźnienie klatki w milisekundach

        for (String frameText : frames) {
            BufferedImage frameImage = createFrameImage(frameText, timerColor, timerSize, timerFont);
            gifEncoder.addFrame(frameImage);
        }

        gifEncoder.finish();
        outputStream.close();

        return outputStream.toByteArray();
    }

    // Metoda tworząca obrazek ramki GIF z tekstem, kolorem, rozmiarem i czcionką
    private BufferedImage createFrameImage(String text, Color timerColor, int timerSize, String timerFont) {
        int width = 400;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setColor(timerColor); // Ustaw kolor tekstu
        g.setFont(new Font(timerFont, Font.PLAIN, timerSize)); // Ustaw czcionkę i rozmiar tekstu

        g.drawString(text, width / 2 - g.getFontMetrics().stringWidth(text) / 2, height / 2);

        g.dispose();
        return image;
    }
}