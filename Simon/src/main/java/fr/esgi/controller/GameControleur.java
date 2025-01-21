package fr.esgi.controller;

import fr.esgi.business.Joueur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GameControleur {
    private List<Joueur> joueurs;
    private int currentPlayerIndex;
    private int currentRound;
    private static final int SEQUENCE_INCREMENT = 4;
    private static final int SEQUENCE_TARGET = 16;

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
        if (shouldEndGame()) {
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

            padControleur.setNumberOfFlashes(SEQUENCE_INCREMENT);
            padControleur.setSoundPaths(soundPaths);

            padControleur.startGame(() -> {
                padControleur.setOnSequenceVerified(this::handleSequenceVerification);
            });

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de pad.fxml");
        }
    }

    private void handleSequenceVerification(boolean isCorrect, List<Integer> sequence) {
        Joueur currentPlayer = joueurs.get(currentPlayerIndex);

        if (!isCorrect) {
            currentPlayer.setActive(false);
            System.out.println(currentPlayer.getName() + " a été éliminé!");

            if (shouldEndGame()) {
                endGame();
                return;
            }
        } else if (sequence.size() >= SEQUENCE_TARGET) {
            System.out.println(currentPlayer.getName() + " a gagné avec une séquence de taille " + SEQUENCE_TARGET);
            endGame();
            return;
        }

        moveToNextPlayer();

        if (currentPlayerIndex == 0) {
            currentRound++;
            System.out.println("Début du round " + currentRound);
        }

        playNextTurn();
    }

    private boolean shouldEndGame() {
        return getActivePlayersCount() == 0 ||
                joueurs.stream().anyMatch(player -> player.getSequenceSize() >= SEQUENCE_TARGET);
    }

    private void moveToNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % joueurs.size();

        while (!joueurs.get(currentPlayerIndex).isActive() && getActivePlayersCount() > 1) {
            currentPlayerIndex = (currentPlayerIndex + 1) % joueurs.size();
        }
    }

    private int getActivePlayersCount() {
        return (int) joueurs.stream().filter(Joueur::isActive).count();
    }

    private void showScoreFinScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/scorefin.fxml"));
            Parent root = loader.load();

            ScoreFinControleur controller = loader.getController();
            controller.setStage(stage);
            controller.setJoueurs(joueurs);

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de scorefin.fxml");
        }
    }

    private void endGame() {
        Optional<Joueur> winnerByTarget = joueurs.stream()
                .filter(j -> j.getSequenceSize() >= SEQUENCE_TARGET)
                .findFirst();

        if (winnerByTarget.isPresent()) {
            Joueur winner = winnerByTarget.get();
            System.out.println("Le gagnant est : " + winner.getName() +
                    " avec un score de " + winner.getScore() +
                    " et une séquence de " + winner.getSequenceSize());
        } else {
            Joueur winner = joueurs.stream()
                    .filter(Joueur::isActive)
                    .findFirst()
                    .orElse(
                            joueurs.stream()
                                    .max(Comparator.comparingInt(Joueur::getScore))
                                    .orElse(null)
                    );

            if (winner != null) {
                System.out.println("Le gagnant est : " + winner.getName() +
                        " avec un score de " + winner.getScore() +
                        " et une séquence de " + winner.getSequenceSize());
            } else {
                System.out.println("Aucun gagnant");
            }
        }

        showScoreFinScene();
    }
}
