module AP.project {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    opens View;
    opens Model;
    opens Controller;
}