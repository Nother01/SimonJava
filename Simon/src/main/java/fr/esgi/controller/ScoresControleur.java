package fr.esgi.controller;

import fr.esgi.business.Joueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ScoresControleur {

    @FXML
    private TableView<Joueur> tableScores;

    @FXML
    private TableColumn<Joueur, String> colNomJoueur;

    @FXML
    private TableColumn<Joueur, Integer> colScoreJoueur;

    @FXML
    private Button backButton;

    private ObservableList<Joueur> joueursObservable;

    @FXML
    public void initialize() {
        // Configuration des colonnes
        colNomJoueur.setCellValueFactory(new PropertyValueFactory<>("name"));
        colScoreJoueur.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Ajouter des joueurs fictifs ou récupérer depuis une source réelle
        joueursObservable = FXCollections.observableArrayList();
        tableScores.setItems(joueursObservable);
    }

    public void setJoueurs(List<Joueur> joueurs) {
        joueursObservable.setAll(joueurs);
    }

    @FXML
    public void afficherEcranAccueil() {
        System.out.println("Retour à l'écran d'accueil.");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/home.fxml"));
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600)); // Taille ajustée pour correspondre aux autres pages
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'écran d'accueil : " + e.getMessage());
        }
    }
}