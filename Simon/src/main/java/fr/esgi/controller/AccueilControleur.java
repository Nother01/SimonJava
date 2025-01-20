package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilControleur {

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    @FXML
    public void onStartButtonClick() {
        System.out.println("Démarrage du jeu depuis l'écran d'accueil.");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/config.fxml"));
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène de configuration : " + e.getMessage());
        }
    }

    @FXML
    public void onQuitButtonClick() {
        System.out.println("Quitter l'application.");
        System.exit(0);
    }

    @FXML
    public void afficherEcranAccueil() {
        System.out.println("Affichage de l'écran d'accueil.");
        // Ajoutez ici le code pour afficher l'écran d'accueil
    }
}