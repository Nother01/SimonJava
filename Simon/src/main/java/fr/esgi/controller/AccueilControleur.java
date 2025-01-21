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
 * Classe contrôleur pour l'écran d'accueil.
 */
public class AccueilControleur {

    @FXML
    private Button soloGameButton;

    @FXML
    private Button quitButton;

    /**
     * Gère l'action lorsque le bouton de jeu solo est cliqué.
     * Charge l'écran de choix du nom des joueurs pour le mode solo.
     */
    @FXML
    public void onsoloGameButtonClick() {
        System.out.println("Démarrage du jeu depuis l'écran d'accueil.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/players_name.fxml"));
            Parent root = loader.load();

            JoueurNomsControleur controller = loader.getController();
            controller.setNumberOfPlayers(1);
            controller.createPlayerNameFields();

            Stage stage = (Stage) soloGameButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la scène de choix du nom des joueurs : " + e.getMessage());
        }
    }

    /**
     * Gère l'action lorsque le bouton quitter est cliqué.
     * Quitte l'application.
     */
    @FXML
    public void onQuitButtonClick() {
        System.out.println("Quitter l'application.");
        System.exit(0);
    }

    /**
     * Affiche l'écran d'accueil.
     */
    @FXML
    public void afficherEcranAccueil() {
        System.out.println("Affichage de l'écran d'accueil.");
    }

    public void afficherCredits(ActionEvent actionEvent) {
        System.out.println("Démarrage du jeu depuis l'écran d'accueil.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/credits.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) soloGameButton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.err.println("c'est cassé acceuil" + e.getMessage());
        }
    }
}