package fr.esgi.business;

import lombok.Data;

import java.util.List;

@Data
public class Partie {
    private List<Joueur> players;
    private List<String> sequence;
    private int currentRound;
    private Joueur currentPlayer;

    public void addToSequence(String color) {
        this.sequence.add(color);
    }

    public void nextRound() {
        this.currentRound++;
    }

    public Joueur getJoueurActuel() {
        return currentPlayer;
    }

    public void nextPlayer() {
        int currentIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currentIndex + 1) % players.size());
    }
}