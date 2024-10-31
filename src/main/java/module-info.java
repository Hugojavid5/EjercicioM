module org.hugo.ejerciciol {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;

    opens org.hugo.ejerciciom to javafx.fxml;
    exports org.hugo.ejerciciom;
    opens Model to javafx.base;
    exports Model;
}