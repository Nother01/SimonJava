package fr.esgi.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    private Joueur joueur;

    @BeforeEach
    void setUp() {
        joueur = new Joueur("TestPlayer", 0);
    }

    @Test
    void testConstructor() {
        assertEquals("TestPlayer", joueur.getName());
        assertEquals(0, joueur.getScore());
        assertTrue(joueur.isActive());
        assertEquals(0, joueur.getSequenceSize());
    }

    @Test
    void testAdjustScore() {
        joueur.adjustScore(10);
        assertEquals(10, joueur.getScore());

        joueur.adjustScore(-5);
        assertEquals(5, joueur.getScore());
    }

    @Test
    void testAddSequence() {
        List<Integer> sequence = Arrays.asList(1, 2, 3);
        joueur.addSequence(sequence);

        assertEquals(3, joueur.getSequenceSize());
        assertTrue(joueur.getSequence().containsAll(sequence));
    }

    @Test
    void testGetSequenceSize() {
        assertEquals(0, joueur.getSequenceSize());

        joueur.addSequence(Arrays.asList(4, 5));
        assertEquals(2, joueur.getSequenceSize());
    }

    @Test
    void testIsActive() {
        assertTrue(joueur.isActive());

        joueur.setActive(false);
        assertFalse(joueur.isActive());
    }
}