package fr.esgi.controller;

import fr.esgi.business.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Classe contrôleur pour la configuration des sons du jeu.
 */
public class ConfigurationControleur {

    @FXML
    private Button btnSound1, btnSound2, btnSound3, btnSound4, btnSave;

    @FXML
    private Label lblSound1, lblSound2, lblSound3, lblSound4;

    @Setter
    private List<Joueur> joueurs;

    /**
     * -- GETTER --
     *  Retourne les chemins des fichiers audio sélectionnés.
     *
     * @return Un tableau de chaînes contenant les chemins des fichiers audio.
     */
    @Getter
    private final String[] soundPaths = new String[4];

    /**
     * Initialise les gestionnaires d'événements pour les boutons de sélection de sons et de sauvegarde.
     */
    @FXML
    public void initialize() {
        btnSound1.setOnAction(event -> selectSound(0, lblSound1));
        btnSound2.setOnAction(event -> selectSound(1, lblSound2));
        btnSound3.setOnAction(event -> selectSound(2, lblSound3));
        btnSound4.setOnAction(event -> selectSound(3, lblSound4));

        btnSave.setOnAction(event -> handleNext());
    }

    /**
     * Ouvre un sélecteur de fichiers pour choisir un fichier audio et met à jour le label correspondant.
     *
     * @param buttonIndex L'index du bouton de sélection de son.
     * @param label Le label à mettre à jour avec le nom du fichier sélectionné.
     */
    private void selectSound(int buttonIndex, Label label) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Sound File");
        // String defaultDirectory = "src/main/resources/fr/esgi/audio/";
        // fileChooser.setInitialDirectory(new File(defaultDirectory));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp3")
        );

        Stage stage = (Stage) label.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            soundPaths[buttonIndex] = selectedFile.getAbsolutePath();

            label.setText(selectedFile.getName());
        } else {
            System.out.println("No file selected.");
        }
    }


    private void handleNext() {
        try {
            Stage primaryStage = (Stage) btnSave.getScene().getWindow();
            GameControleur GameControleur = new GameControleur(joueurs, primaryStage);
            GameControleur.setSoundPaths(soundPaths);
            GameControleur.startGame();
        } catch (Exception e) {
            System.err.println("Erreur lors du lancement du jeu : " + e.getMessage());
            e.printStackTrace();
        }
    }
}