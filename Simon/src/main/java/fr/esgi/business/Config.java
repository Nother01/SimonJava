package fr.esgi.business;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class Config {

    private Map<Musique, String> couleurMusiqueAssocié = new HashMap<>();
    private int nbJoueurs;

}
