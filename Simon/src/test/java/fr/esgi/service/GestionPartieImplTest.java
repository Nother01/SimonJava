package fr.esgi.service;

import fr.esgi.business.Joueur;
import fr.esgi.service.impl.GestionPartieImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionPartieImplTest {

    private GestionPartieImpl gestionPartie;

    @BeforeEach
    public void setUp() {
        gestionPartie = new GestionPartieImpl();
    }

    @Test
    public void testDemarrerPartie() {
        gestionPartie.ajouterJoueur(new Joueur());
        gestionPartie.demarrerPartie();

        assertEquals(1, gestionPartie.getSequence().size(), "La séquence doit contenir une couleur après le démarrage.");
        assertEquals(1, gestionPartie.getCurrentRound(), "Le round doit être initialisé à 1.");
    }

    @Test
    public void testValiderSequenceCorrecte() {
        gestionPartie.demarrerPartie();
        List<String> sequence = gestionPartie.getSequence();

        assertTrue(gestionPartie.validerSequence(sequence), "La séquence correcte doit être validée.");
    }

    @Test
    public void testValiderSequenceIncorrecte() {
        gestionPartie.demarrerPartie();
        List<String> mauvaiseSequence = Arrays.asList("Rouge", "Vert");

        assertFalse(gestionPartie.validerSequence(mauvaiseSequence), "Une séquence incorrecte ne doit pas être validée.");
    }

    @Test
    public void testGenererProchaineCouleur() {
        gestionPartie.demarrerPartie();
        int tailleInitiale = gestionPartie.getSequence().size();

        gestionPartie.genererProchaineCouleur();

        assertEquals(tailleInitiale + 1, gestionPartie.getSequence().size(), "La séquence doit s'agrandir d'une couleur.");
    }

    @Test
    public void testAjouterJoueur() {
        Joueur joueur = new Joueur();
        gestionPartie.ajouterJoueur(joueur);

        assertEquals(1, gestionPartie.getJoueurs().size(), "Un joueur doit être ajouté.");
    }

    @Test
    public void testGetJoueurActuelAvecAucunJoueur() {
        assertNull(gestionPartie.getJoueurActuel(), "Sans joueur, le joueur actuel doit être null.");
    }

    @Test
    public void testGetJoueurActuelAvecUnJoueur() {
        Joueur joueur = new Joueur();
        joueur.setName("Alice");
        gestionPartie.ajouterJoueur(joueur);

        assertEquals(joueur, gestionPartie.getJoueurActuel(), "Le joueur actuel doit être Alice.");
    }

    @Test
    public void testPasserAuJoueurSuivant() {
        Joueur joueur1 = new Joueur();
        joueur1.setName("Alice");
        Joueur joueur2 = new Joueur();
        joueur2.setName("Bob");

        gestionPartie.ajouterJoueur(joueur1);
        gestionPartie.ajouterJoueur(joueur2);

        assertEquals(joueur1, gestionPartie.getJoueurActuel(), "Le joueur actuel doit être Alice.");

        gestionPartie.passerAuJoueurSuivant();
        assertEquals(joueur2, gestionPartie.getJoueurActuel(), "Le joueur actuel doit être Bob.");
    }
}