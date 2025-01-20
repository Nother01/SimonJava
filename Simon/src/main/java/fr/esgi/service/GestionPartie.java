package fr.esgi.service;

import fr.esgi.business.Joueur;
import java.util.List;

// Interface GestionPartie
public interface GestionPartie {
    void demarrerPartie();
    boolean validerSequence(List<String> sequenceJoueur);
    void genererProchaineCouleur();
}
