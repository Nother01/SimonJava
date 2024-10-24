package fr.esgi.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Partie {

    private List<Joueur> joueurs;
    private List<String> sequenceCouleurs;
    private int nivauActuel;
    private boolean partieTerminee;
}
