package fr.esgi.business;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Mouvement {

    private String couleur;
    private Long timestamp;
}
