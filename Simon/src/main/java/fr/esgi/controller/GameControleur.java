package fr.esgi.controller;

import fr.esgi.business.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameControleur {
    private List<Joueur> joueurs;
    private int currentPlayerIndex;
    private int currentRound;
    private static final int SEQUENCE_INCREMENT = 4;

    @Setter
    private String[] soundPaths = new String[4];

    @Getter
    private Stage stage;

    public GameControleur(List<Joueur> joueurs, Stage stage) {
        this.joueurs = new ArrayList<>(joueurs);
        this.currentPlayerIndex = 0;
        this.currentRound = 1;
        this.stage = stage;
    }

    public void startGame() {
        if (joueurs.isEmpty()) {
            System.out.println("Aucun joueur disponible");
            return;
        }
        playNextTurn();
    }

    private void playNextTurn() {
        if (getActivePlayersCount() <= 1) {
            endGame();
            return;
        }

        while (!joueurs.get(currentPlayerIndex).isActive()) {
            moveToNextPlayer();
        }

        Joueur currentPlayer = joueurs.get(currentPlayerIndex);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/pad.fxml"));
            Parent root = loader.load();

            PadControleur padControleur = loader.getController();
            padControleur.setJoueur(currentPlayer);

            // int numberOfFlashes = currentPlayer.getSequence().size() + SEQUENCE_INCREMENT;
            padControleur.setNumberOfFlashes(SEQUENCE_INCREMENT);
            padControleur.setSoundPaths(soundPaths);

            padControleur.startGame(() -> {
                padControleur.setOnSequenceVerified(this::handleSequenceVerification);
            });

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de pad.fxml");
        }
    }

    private void handleSequenceVerification(boolean isCorrect, List<Integer> sequence) {
        Joueur currentPlayer = joueurs.get(currentPlayerIndex);

        if (!isCorrect) {
            currentPlayer.setActive(false);
            System.out.println(currentPlayer.getName() + " a été éliminé!");
        }

        moveToNextPlayer();

        if (currentPlayerIndex == 0) {
            currentRound++;
            System.out.println("Début du round " + currentRound);
        }

        playNextTurn();
    }

    private void moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % joueurs.size();
    }

    private int getActivePlayersCount() {
        return (int) joueurs.stream().filter(Joueur::isActive).count();
    }

    private void endGame() {
        Joueur winner = joueurs.stream()
                .filter(Joueur::isActive)
                .findFirst()
                .orElse(null);

        if (winner != null) {
            System.out.println("Le gagnant est : " + winner.getName() +
                    " avec un score de " + winner.getScore());
        } else {
            System.out.println("Aucun gagnant");
        }
    }
}
