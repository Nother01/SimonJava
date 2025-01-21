package fr.esgi.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PadController {
    @FXML
    private Rectangle rectRed, rectBlue, rectGreen, rectYellow;

    @Setter
    private List<String> playerNames = new ArrayList<>();

    @Setter
    private String[] soundPaths = new String[4];

    @Setter
    private int numberOfFlashes;

    private List<Integer> sequence = new ArrayList<>();
    private final Random random = new Random();

    @FXML
    public void initialize() {
    }

    public void startGame() {
        generateSequence();
        playSequence();
    }

    private void generateSequence() {
        sequence.clear();
        for (int i = 0; i < numberOfFlashes; i++) {
            sequence.add(random.nextInt(4)); // 0: Red, 1: Blue, 2: Green, 3: Yellow
        }
        System.out.println("Sequence generated: " + sequence);
    }

    private void playSequence() {
        for (int i = 0; i < sequence.size(); i++) {
            int index = i;
            PauseTransition delay = new PauseTransition(Duration.seconds(i * 1.5));
            delay.setOnFinished(event -> flashRectangle(sequence.get(index)));
            delay.play();
        }
    }

    private void flashRectangle(int colorIndex) {
        Rectangle rectToFlash;
        switch (colorIndex) {
            case 0:
                rectToFlash = rectRed;
                break;
            case 1:
                rectToFlash = rectBlue;
                break;
            case 2:
                rectToFlash = rectGreen;
                break;
            case 3:
                rectToFlash = rectYellow;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + colorIndex);
        }

        Color originalColor = (Color) rectToFlash.getFill();
        rectToFlash.setFill(Color.WHITE);

        new Thread(() -> SoundControleur.playSound(soundPaths[colorIndex])).start();

        PauseTransition flashDuration = new PauseTransition(Duration.seconds(1));
        flashDuration.setOnFinished(event -> rectToFlash.setFill(originalColor));
        flashDuration.play();
    }

    private void playSoundForSquare(int colorIndex) {
        if (colorIndex >= 0 && colorIndex < soundPaths.length) {
            SoundControleur.playSound(soundPaths[colorIndex]);
        }
    }
}
