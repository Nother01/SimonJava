package fr.esgi.service.impl;

import fr.esgi.business.Joueur;

import java.util.List;

public class ScoresServiceImpl {

    /**
     * Retourne une liste de joueurs avec leurs scores.
     *
     * @param joueurs Liste des joueurs
     * @return Liste des joueurs inchangée, mais utilisée pour peupler la table des scores
     */
    public List<Joueur> getScores(List<Joueur> joueurs) {
        return joueurs;
    }
}
