package fr.esgi.service.impl;

import fr.esgi.business.Joueur;
import fr.esgi.service.GestionJoueur;
import fr.esgi.service.GestionPartie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Classe GestionPartieImpl
public class GestionPartieImpl implements GestionPartie {
    private List<String> sequence;
    private int currentRound;
    private List<Joueur> joueurs;
    private int indexJoueurActuel;

    public GestionPartieImpl() {
        this.sequence = new ArrayList<>();
        this.currentRound = 0;
        this.joueurs = new ArrayList<>();
        this.indexJoueurActuel = 0;
    }

    @Override
    public void demarrerPartie() {
        sequence.clear();
        currentRound = 0;
        genererProchaineCouleur();
        if (!joueurs.isEmpty()) {
            indexJoueurActuel = 0;
        }
    }

    @Override
    public boolean validerSequence(List<String> sequenceJoueur) {
        for (int i = 0; i < sequenceJoueur.size(); i++) {
            if (!sequenceJoueur.get(i).equals(sequence.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void genererProchaineCouleur() {
        String[] couleurs = {"Rouge", "Vert", "Bleu", "Jaune"};
        Random random = new Random();
        sequence.add(couleurs[random.nextInt(couleurs.length)]);
        currentRound++;
    }

    public List<String> getSequence() {
        return new ArrayList<>(sequence);
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void ajouterJoueur(Joueur joueur) {
        joueurs.add(joueur);
    }

    public Joueur getJoueurActuel() {
        if (joueurs.isEmpty()) {
            return null;
        }
        return joueurs.get(indexJoueurActuel);
    }

    public void passerAuJoueurSuivant() {
        if (!joueurs.isEmpty()) {
            indexJoueurActuel = (indexJoueurActuel + 1) % joueurs.size();
        }
    }

    public List<Joueur> getJoueurs() {
        return new ArrayList<>(joueurs);
    }
}