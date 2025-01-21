package fr.esgi.service;

import fr.esgi.business.Joueur;
import fr.esgi.service.impl.GestionJoueurImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionJoueurImplTest {

    private GestionJoueurImpl gestionJoueur;

    @BeforeEach
    public void setUp() {
        gestionJoueur = new GestionJoueurImpl();
    }

    @Test
    public void testAjouterJoueur() {
        gestionJoueur.ajouterJoueur("Alice");
        List<Joueur> joueurs = gestionJoueur.getTousLesJoueurs();

        assertEquals(1, joueurs.size(), "La liste doit contenir un joueur.");
        assertEquals("Alice", joueurs.get(0).getName(), "Le nom du joueur doit être Alice.");
    }

    @Test
    public void testObtenirJoueurActuelAvecAucunJoueur() {
        Joueur joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertNull(joueurActuel, "Lorsque la liste est vide, le joueur actuel doit être null.");
    }

    @Test
    public void testObtenirJoueurActuelAvecUnJoueur() {
        gestionJoueur.ajouterJoueur("Alice");
        Joueur joueurActuel = gestionJoueur.obtenirJoueurActuel();

        assertNotNull(joueurActuel, "Le joueur actuel ne doit pas être null.");
        assertEquals("Alice", joueurActuel.getName(), "Le nom du joueur actuel doit être Alice.");
    }

    @Test
    public void testPasserAuJoueurSuivant() {
        gestionJoueur.ajouterJoueur("Alice");
        gestionJoueur.ajouterJoueur("Bob");

        Joueur joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Alice", joueurActuel.getName(), "Le premier joueur doit être Alice.");

        gestionJoueur.passerAuJoueurSuivant();
        joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Bob", joueurActuel.getName(), "Le joueur suivant doit être Bob.");

        gestionJoueur.passerAuJoueurSuivant();
        joueurActuel = gestionJoueur.obtenirJoueurActuel();
        assertEquals("Alice", joueurActuel.getName(), "Après Bob, le joueur doit être Alice.");
    }

    @Test
    public void testGetTousLesJoueurs() {
        gestionJoueur.ajouterJoueur("Alice");
        gestionJoueur.ajouterJoueur("Bob");

        List<Joueur> joueurs = gestionJoueur.getTousLesJoueurs();

        assertEquals(2, joueurs.size(), "La liste doit contenir deux joueurs.");
        assertEquals("Alice", joueurs.get(0).getName(), "Le premier joueur doit être Alice.");
        assertEquals("Bob", joueurs.get(1).getName(), "Le deuxième joueur doit être Bob.");
    }
}

