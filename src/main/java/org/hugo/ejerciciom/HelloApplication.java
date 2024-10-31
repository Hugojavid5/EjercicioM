package org.hugo.ejerciciom;

import BBDD.ConexionBBDD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

/**
 * Clase principal de la aplicación que gestiona la inicialización de la ventana de inicio de sesión.
 */
public class HelloApplication extends Application {
     static Stage stage;

    /**
     * Método que inicia la aplicación y carga la interfaz de usuario.
     *
     * @param s El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage s) throws IOException {
        stage = s;
        Properties connConfig = ConexionBBDD.loadProperties();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 250, 150);
        stage.setResizable(false);
        stage.setTitle("Login de aviones");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método que devuelve el escenario principal de la aplicación.
     *
     * @return El escenario principal de la aplicación.
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}
