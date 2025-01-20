package fr.esgi.service;

import fr.esgi.business.Joueur;

public interface GestionJoueur {
    void ajouterJoueur(Joueur joueur);
    Joueur obtenirJoueurActuel();
    void mettreAJourScore(Joueur joueur, int changementScore);
}
