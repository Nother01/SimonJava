package fr.esgi.controller;

import fr.esgi.business.Joueur;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreFinControleur {
    @FXML
    private ListView<String> scoreListView;

    @FXML
    private Label winnerLabel;

    @Setter
    private Stage stage;

    public void setJoueurs(List<Joueur> joueurs) {
        List<Joueur> sortedJoueurs = joueurs.stream()
                .sorted((j1, j2) -> Integer.compare(j2.getScore(), j1.getScore()))
                .collect(Collectors.toList());

        scoreListView.getItems().clear();
        for (int i = 0; i < sortedJoueurs.size(); i++) {
            Joueur joueur = sortedJoueurs.get(i);
            scoreListView.getItems().add((i + 1) + ". " + joueur.getName() + " - Score: " + joueur.getScore());
        }

        if (!sortedJoueurs.isEmpty()) {
            Joueur winner = sortedJoueurs.get(0);
            winnerLabel.setText("Gagnant : " + winner.getName() + " avec " + winner.getScore() + " points !");
        } else {
            winnerLabel.setText("Aucun gagnant !");
        }
    }
}
