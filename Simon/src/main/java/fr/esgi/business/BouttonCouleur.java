package fr.esgi.business;

import lombok.Data;

@Data
public class BouttonCouleur {
    private String color;
    private String soundFilePath;

    public void playSound() {
        // Cette méthode jouera un son basé sur soundFilePath
        System.out.println("Playing sound from: " + soundFilePath);
    }
}