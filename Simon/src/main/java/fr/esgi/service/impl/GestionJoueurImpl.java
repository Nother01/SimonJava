package fr.esgi.service.impl;

import fr.esgi.business.Joueur;

import java.util.ArrayList;
import java.util.List;

public class GestionJoueurImpl {

    private final List<Joueur> joueurs = new ArrayList<>();
    private int indexJoueurActuel = 0;

    public void ajouterJoueur(String nom) {
        Joueur joueur = new Joueur();
        joueur.setName(nom);
        joueurs.add(joueur);
    }

    public Joueur obtenirJoueurActuel() {
        if (joueurs.isEmpty()) {
            return null;
        }
        return joueurs.get(indexJoueurActuel);
    }

    public List<Joueur> getTousLesJoueurs() {
        return new ArrayList<>(joueurs);
    }

    public void passerAuJoueurSuivant() {
        indexJoueurActuel = (indexJoueurActuel + 1) % joueurs.size();
    }
}

