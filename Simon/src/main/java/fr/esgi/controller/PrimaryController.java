package fr.esgi.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import fr.esgi.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
