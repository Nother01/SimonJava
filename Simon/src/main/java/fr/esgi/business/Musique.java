package fr.esgi.business;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Musique {
    private List<String> notes;
    private String nom;
}
