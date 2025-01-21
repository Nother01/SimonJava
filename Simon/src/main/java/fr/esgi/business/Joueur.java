package fr.esgi.business;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class Joueur {
    @Getter
    private boolean active;

    private String name;
    @Getter
    private int score;

    private List<Integer> sequence;

    public Joueur(String name, int score) {
        this.name = name;
        this.score = score;
        this.active = true;
        this.sequence = new ArrayList<>();
    }

    public void adjustScore(int scoreChange) {
        this.score += scoreChange;
    }

    public void addSequence(List<Integer> newSequence) {
        this.sequence.addAll(newSequence);
    }

    public int getSequenceSize() {
        return sequence.size();
    }
}
