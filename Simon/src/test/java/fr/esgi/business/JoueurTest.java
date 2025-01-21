package fr.esgi.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JoueurTest {

    private Joueur joueur;

    @BeforeEach
    public void setUp() {
        joueur = new Joueur();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(joueur);
        assertNull(joueur.getName(), "Le nom par défaut doit être null.");
        assertEquals(0, joueur.getScore(), "Le score par défaut doit être 0.");
    }

    @Test
    public void testSetName() {
        joueur.setName("Alice");
        assertEquals("Alice", joueur.getName(), "Le nom doit être correctement défini.");
    }

    @Test
    public void testSetScore() {
        joueur.setScore(10);
        assertEquals(10, joueur.getScore(), "Le score doit être correctement défini.");
    }

    @Test
    public void testAdjustScorePositiveChange() {
        joueur.setScore(10);
        joueur.adjustScore(5);
        assertEquals(15, joueur.getScore(), "Le score doit augmenter correctement avec une valeur positive.");
    }

    @Test
    public void testAdjustScoreNegativeChange() {
        joueur.setScore(10);
        joueur.adjustScore(-3);
        assertEquals(7, joueur.getScore(), "Le score doit diminuer correctement avec une valeur négative.");
    }

    @Test
    public void testAdjustScoreZeroChange() {
        joueur.setScore(10);
        joueur.adjustScore(0);
        assertEquals(10, joueur.getScore(), "Le score ne doit pas changer avec une valeur de 0.");
    }
}