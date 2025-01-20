package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class JeuControleur {

    @FXML
    private Button backButton;

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
    public void jouerTour() {
        System.out.println("Jouer tour.");
        // Your code for jouerTour
    }
}