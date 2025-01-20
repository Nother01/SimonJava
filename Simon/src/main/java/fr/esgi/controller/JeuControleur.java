package fr.esgi.controller;

import fr.esgi.business.Joueur;
import fr.esgi.service.impl.GestionJoueurImpl;
import fr.esgi.service.impl.GestionPartieImpl;
import javafx.fxml.FXML;


public class JeuControleur {
    private final GestionPartieImpl gestionPartie;
    private final GestionJoueurImpl gestionJoueur;

    public JeuControleur() {
        this.gestionPartie = new GestionPartieImpl();
        this.gestionJoueur = new GestionJoueurImpl();
    }

    @FXML
    public void jouerTour() {
        Joueur joueurActuel = gestionJoueur.obtenirJoueurActuel();
        if (joueurActuel != null) {
            System.out.println("C'est le tour de : " + joueurActuel.getName());
        } else {
            System.out.println("Veuillez ajouter des joueurs avant de commencer.");
        }
    }
}