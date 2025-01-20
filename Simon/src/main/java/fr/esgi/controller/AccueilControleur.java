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
    private Button soloGameButton;

    @FXML
    private Button quitButton;

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

    @FXML
    public void onQuitButtonClick() {
        System.out.println("Quitter l'application.");
        System.exit(0);
    }

    @FXML
    public void afficherEcranAccueil() {
        System.out.println("Affichage de l'écran d'accueil.");
    }
}