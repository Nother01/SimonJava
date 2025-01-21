package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.esgi.business.Joueur;

/**
 * Classe contrôleur pour la gestion des noms des joueurs.
 */
public class JoueurNomsControleur {
    @FXML
    private GridPane playerGrid;

    @FXML
    private Button btnNext;

    @Setter
    private int numberOfPlayers;
    private final List<TextField> playerNameFields = new ArrayList<>();

    /**
     * Initialise le contrôleur et configure l'action du bouton suivant.
     */
    @FXML
    public void initialize() {
        btnNext.setOnAction(event -> handleNext());
    }

    /**
     * Crée les champs de saisie pour les noms des joueurs en fonction du nombre de joueurs.
     */
    public void createPlayerNameFields() {
        playerGrid.getChildren().clear();
        playerNameFields.clear();

        for (int i = 0; i < numberOfPlayers; i++) {
            Label label = new Label("Player " + (i + 1) + ":");
            TextField textField = new TextField();
            textField.setPromptText("Enter name");

            playerGrid.add(label, 0, i);
            playerGrid.add(textField, 1, i);
            playerNameFields.add(textField);
        }
    }

    /**
     * Gère l'action du bouton suivant en vérifiant les noms des joueurs et en passant à l'écran suivant.
     */
    private void handleNext() {
        List<Joueur> joueurs = new ArrayList<>();
        for (TextField textField : playerNameFields) {
            String name = textField.getText().trim();
            if (name.isEmpty()) {
                textField.setStyle("-fx-border-color: red;");
                return;
            }
            joueurs.add(new Joueur(name, 0)); // Initialise le score à 0
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/config.fxml"));
            Stage stage = (Stage) btnNext.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            ConfigurationControleur configController = loader.getController();
            configController.setJoueurs(joueurs);
        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}