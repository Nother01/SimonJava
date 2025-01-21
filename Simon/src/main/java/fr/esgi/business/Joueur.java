package fr.esgi.business;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class Joueur {

    private String name;
    private Integer score;

    public Joueur(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void adjustScore(int scoreChange) {
        this.score += scoreChange;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
}