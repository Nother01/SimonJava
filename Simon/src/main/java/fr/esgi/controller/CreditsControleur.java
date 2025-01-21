package fr.esgi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe contrôleur pour l'écran des crédits.
 */
public class CreditsControleur {

    @FXML
    private Button soloGameButton;

    /**
     * Affiche les crédits du jeu.
     */
    public void afficherCredits() {
        System.out.println("Développé par l'équipe Simon.");
    }

    public void afficherEcranAccueil(ActionEvent actionEvent) {
        System.out.println("Démarrage du jeu depuis l'écran d'accueil.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/home.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) soloGameButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("c'est cassé" + e.getMessage());
        }
    }
}