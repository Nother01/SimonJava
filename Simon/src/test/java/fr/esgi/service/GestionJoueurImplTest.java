package fr.esgi.service;

import fr.esgi.business.Joueur;
import fr.esgi.service.impl.GestionJoueurImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionJoueurImplTest {

    private GestionJoueurImpl gestionJoueur;

    @BeforeEach
    void setUp() {
        gestionJoueur = new GestionJoueurImpl();
    }

    @Test
    void testAjouterJoueur() {
        gestionJoueur.ajouterJoueur("Player1");
        List<Joueur> joueurs = gestionJoueur.getTousLesJoueurs();

        assertEquals(1, joueurs.size());
        assertEquals("Player1", joueurs.get(0).getName());
        assertEquals(0, joueurs.get(0).getScore());
    }

    @Test
    void testObtenirJoueurActuel() {
        assertNull(gestionJoueur.obtenirJoueurActuel());

        gestionJoueur.ajouterJoueur("Player1");
        Joueur joueurActuel = gestionJoueur.obtenirJoueurActuel();

        assertNotNull(joueurActuel);
        assertEquals("Player1", joueurActuel.getName());
    }

    @Test
    void testGetTousLesJoueurs() {
        gestionJoueur.ajouterJoueur("Player1");
        gestionJoueur.ajouterJoueur("Player2");

        List<Joueur> joueurs = gestionJoueur.getTousLesJoueurs();

        assertEquals(2, joueurs.size());
        assertEquals("Player1", joueurs.get(0).getName());
        assertEquals("Player2", joueurs.get(1).getName());
    }

    @Test
    void testPasserAuJoueurSuivant() {
        gestionJoueur.ajouterJoueur("Player1");
        gestionJoueur.ajouterJoueur("Player2");
        gestionJoueur.ajouterJoueur("Player3");

        Joueur joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Player1", joueurActuel.getName());

        gestionJoueur.passerAuJoueurSuivant();
        joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Player2", joueurActuel.getName());

        gestionJoueur.passerAuJoueurSuivant();
        joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Player3", joueurActuel.getName());

        gestionJoueur.passerAuJoueurSuivant();
        joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Player1", joueurActuel.getName());
    }
}
