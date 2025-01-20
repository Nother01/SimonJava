package fr.esgi.controller;

import fr.esgi.business.Joueur;
import fr.esgi.service.impl.GestionJoueurImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class JoueurControleur {

    private final GestionJoueurImpl gestionJoueur = new GestionJoueurImpl();

    @FXML
    private TextField inputNomJoueur;

    @FXML
    private ListView<String> listeJoueurs;

    private ObservableList<String> joueursObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listeJoueurs.setItems(joueursObservableList);
    }

    @FXML
    public void ajouterJoueur() {
        String nom = inputNomJoueur.getText();
        if (nom != null && !nom.isEmpty()) {
            gestionJoueur.ajouterJoueur(nom); // Appel au service
            joueursObservableList.add(nom);  // Mise à jour de la liste observable
            inputNomJoueur.clear();
        } else {
            System.out.println("Veuillez entrer un nom valide.");
        }
    }

    @FXML
    public void afficherJoueurActuel() {
        var joueurActuel = gestionJoueur.obtenirJoueurActuel(); // Appel au service
        if (joueurActuel != null) {
            System.out.println("Joueur actuel : " + joueurActuel.getName());
        } else {
            System.out.println("Aucun joueur actuel.");
        }
    }
}