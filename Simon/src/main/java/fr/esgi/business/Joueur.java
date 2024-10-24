package fr.esgi.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class Joueur {

    private Long id;
    private String prenom;
    private int score;
    private Boolean estActif;
    private List<Mouvement> mouvements;
    private static Long idCompteur = 0L;

}
