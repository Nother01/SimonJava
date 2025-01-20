package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfigurationControleur {

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> player1SoundChoice;

    @FXML
    private ChoiceBox<String> player2SoundChoice;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        // Initialize choice boxes with sound options
        player1SoundChoice.getItems().addAll("Sound1", "Sound2", "Sound3");
        player2SoundChoice.getItems().addAll("Sound1", "Sound2", "Sound3");
    }

    @FXML
    public void onBackButtonClick() {
        System.out.println("Retour à l'écran d'accueil.");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/home.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600)); // Taille ajustée pour correspondre aux autres pages
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'écran d'accueil : " + e.getMessage());
        }
    }

    @FXML
    public void onSaveButtonClick() {
        String player1Sound = player1SoundChoice.getValue();
        String player2Sound = player2SoundChoice.getValue();
        System.out.println("Configuration sauvegardée : Joueur 1 - " + player1Sound + ", Joueur 2 - " + player2Sound);
        // Ajoutez ici le code pour sauvegarder la configuration des sons

        // Load the game scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/games.fxml")); // Ensure the path is correct
            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600)); // Taille ajustée pour correspondre aux autres pages
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène de jeu : " + e.getMessage());
        }
    }
}