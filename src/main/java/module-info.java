module org.hugo.ejerciciom {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.hugo.ejerciciom to javafx.fxml;
    exports org.hugo.ejerciciom;
}