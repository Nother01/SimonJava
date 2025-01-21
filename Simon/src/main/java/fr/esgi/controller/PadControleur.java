package fr.esgi.controller;

import fr.esgi.business.Joueur;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class PadControleur {
    @FXML
    private Rectangle rectRed, rectBlue, rectGreen, rectYellow;

    @Setter
    private Joueur joueur;

    @Setter
    private String[] soundPaths = new String[4];

    @Setter
    private int numberOfFlashes;

    private List<Integer> sequence = new ArrayList<>();
    private final Random random = new Random();

    @FXML
    private Label gameMessage;

    @FXML
    private Button btnVerify;

    private List<Integer> userSequence = new ArrayList<>();
    private boolean isUserTurn = false;

    @FXML
    public void initialize() {
        rectRed.setOnMouseClicked(event -> handleRectangleClick(rectRed));
        rectBlue.setOnMouseClicked(event -> handleRectangleClick(rectBlue));
        rectGreen.setOnMouseClicked(event -> handleRectangleClick(rectGreen));
        rectYellow.setOnMouseClicked(event -> handleRectangleClick(rectYellow));

        btnVerify.setOnAction(event -> verifySequence());
    }

    private BiConsumer<Boolean, List<Integer>> onSequenceVerified;

    public void setOnSequenceVerified(BiConsumer<Boolean, List<Integer>> callback) {
        this.onSequenceVerified = callback;
    }

    public void startGame(Runnable onSequenceComplete) {
        if (joueur == null) {
            gameMessage.setText("Aucun joueur n'est défini.");
            return;
        }

        userSequence.clear();
        isUserTurn = false;
        btnVerify.setDisable(true);
        gameMessage.setText("Mémorisez...");

        generateSequence();
        playSequence(() -> {
            gameMessage.setText("À vous de jouer, " + joueur.getName() + "...");
            isUserTurn = true;
            btnVerify.setDisable(false);
            onSequenceComplete.run();
        });
    }

    private void generateSequence() {
        sequence.clear();
        for (int i = 0; i < numberOfFlashes; i++) {
            sequence.add(random.nextInt(4)); // 0: Red, 1: Blue, 2: Green, 3: Yellow
        }
        joueur.addSequence(sequence);
        sequence = joueur.getSequence();
        System.out.println("Sequence: " + sequence);
    }

    private void playSequence(Runnable onSequenceComplete) {
        for (int i = 0; i < sequence.size(); i++) {
            int index = i;
            PauseTransition delay = new PauseTransition(Duration.seconds(i * 1.5));
            delay.setOnFinished(event -> flashRectangle(sequence.get(index)));
            delay.play();
        }

        PauseTransition endDelay = new PauseTransition(Duration.seconds(sequence.size() * 1.5));
        endDelay.setOnFinished(event -> onSequenceComplete.run());
        endDelay.play();
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

    @FXML
    private void handleRectangleClick(Rectangle clickedRectangle) {
        if (!isUserTurn) return;

        int colorIndex;
        if (clickedRectangle == rectRed) colorIndex = 0;
        else if (clickedRectangle == rectBlue) colorIndex = 1;
        else if (clickedRectangle == rectGreen) colorIndex = 2;
        else if (clickedRectangle == rectYellow) colorIndex = 3;
        else throw new IllegalArgumentException("Unknown rectangle");

        userSequence.add(colorIndex);
        flashRectangle(colorIndex);
    }

    @FXML
    private void verifySequence() {
        boolean isCorrect = sequence.equals(userSequence);
        if (isCorrect) {
            gameMessage.setText("Bravo ! Séquence correcte.");
            joueur.adjustScore(2);
        } else {
            gameMessage.setText("Erreur ! Essayez encore.");
            joueur.adjustScore(-2);
        }

        btnVerify.setDisable(true);
        isUserTurn = false;

        if (onSequenceVerified != null) {
            onSequenceVerified.accept(isCorrect, new ArrayList<>(sequence));
        }
    }
}