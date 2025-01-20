package fr.esgi.business;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Classe Player
@Data
@NoArgsConstructor
public class Joueur {

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void adjustScore(int scoreChange) {
        this.score += scoreChange;
    }
}
