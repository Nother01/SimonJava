package fr.esgi.controller;

import fr.esgi.business.Joueur;
import fr.esgi.service.impl.ScoresServiceImpl;
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

import java.awt.event.ActionEvent;
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

    private final ScoresServiceImpl scoresService = new ScoresServiceImpl();

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

    public void afficherScores(List<Joueur> joueurs) {
        // Récupère les scores des joueurs via le service
        List<Joueur> joueursAvecScores = scoresService.getScores(joueurs);
        joueursObservable.setAll(joueursAvecScores);
    }

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