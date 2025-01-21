package fr.esgi.business;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Joueur {

    private String name;
    private Integer score;

    public void adjustScore(int scoreChange) {
        this.score += scoreChange;
    }
}