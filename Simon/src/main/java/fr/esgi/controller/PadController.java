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

/**
 * Classe contrôleur pour la gestion du pad de jeu.
 */
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

    /**
     * Démarre le jeu en générant et en jouant la séquence.
     */
    public void startGame() {
        generateSequence();
        playSequence();
    }

    /**
     * Génère une séquence aléatoire de couleurs.
     */
    private void generateSequence() {
        sequence.clear();
        for (int i = 0; i < numberOfFlashes; i++) {
            sequence.add(random.nextInt(4)); // 0: Rouge, 1: Bleu, 2: Vert, 3: Jaune
        }
        System.out.println("Sequence generated: " + sequence);
    }

    /**
     * Joue la séquence de couleurs générée.
     */
    private void playSequence() {
        for (int i = 0; i < sequence.size(); i++) {
            int index = i;
            PauseTransition delay = new PauseTransition(Duration.seconds(i * 1.5));
            delay.setOnFinished(event -> flashRectangle(sequence.get(index)));
            delay.play();
        }
    }

    /**
     * Fait clignoter le rectangle correspondant à l'index de couleur donné.
     *
     * @param colorIndex L'index de la couleur à faire clignoter.
     */
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

        PauseTransition flashDuration = new PauseTransition(Duration.seconds(0.5));
        flashDuration.setOnFinished(event -> rectToFlash.setFill(originalColor));
        flashDuration.play();
    }
}