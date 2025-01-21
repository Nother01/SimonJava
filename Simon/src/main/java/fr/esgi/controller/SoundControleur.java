package fr.esgi.controller;

import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class SoundControleur {
    public static void playSound(String soundPath) {
        if (soundPath == null || soundPath.isEmpty()) {
            System.out.println("No sound path provided");
            return;
        }

        try (FileInputStream fis = new FileInputStream(soundPath)) {
            Player player = new Player(fis);
            player.play();
        } catch (Exception e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}

