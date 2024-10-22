module fr.esgi {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.esgi to javafx.fxml;
    exports fr.esgi;
}
