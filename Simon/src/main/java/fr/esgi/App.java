package fr.esgi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App pour le jeu Simon
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // Charger l'écran d'accueil (home.fxml)
        scene = new Scene(loadFXML("home"), 800, 600); // Taille ajustée pour une meilleure interface
        stage.setTitle("Jeu Simon"); // Ajout du titre de la fenêtre
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Change la racine de la scène pour un nouvel écran FXML.
     *
     * @param fxml Nom du fichier FXML (sans extension)
     * @throws IOException Si le fichier FXML n'est pas trouvé
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Charge un fichier FXML et retourne l'objet Parent correspondant.
     *
     * @param fxml Nom du fichier FXML (sans extension)
     * @return Objet Parent chargé
     * @throws IOException Si le fichier FXML n'est pas trouvé
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fr/esgi/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(); // Démarrage de l'application JavaFX
    }
}