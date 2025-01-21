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
    private Button creditButton;

    @FXML
    private Button backButton;

    /**
     * Affiche les crédits du jeu.
     */
    public void afficherCredits() {
        System.out.println("Développé par l'équipe Simon.");
    }

    /**
     * Gère l'action lorsque le bouton de jeu solo est cliqué.
     * Charge l'écran de choix du nom des joueurs pour le mode solo.
     */

    @FXML
    private void afficherEcranAccueil(ActionEvent event) {
        try {
            // Chargement de la vue "home.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/home.fxml"));
            Parent root = loader.load();

            // Récupération de la scène actuelle
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'écran d'accueil : " + e.getMessage());
        }
    }
}