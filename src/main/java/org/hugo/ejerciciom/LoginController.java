package org.hugo.ejerciciom;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import Dao.DaoUsuario;
import Model.ModelUsuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;

/**
 * Controlador para la funcionalidad de inicio de sesión en la aplicación.
 */
public class LoginController implements Initializable {

    @FXML
    private Button btt_login;  // Botón para iniciar sesión

    @FXML
    private TextField txt_password;  // Campo de texto para la contraseña

    @FXML
    private TextField txt_usuario;  // Campo de texto para el nombre de usuario

    private DaoUsuario daoUsuario;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (btt_login != null) {
            btt_login.setDefaultButton(true); // Establecer el botón Login como predeterminado
        }
    }

    /**
     * Maneja el evento de clic en el botón de inicio de sesión.
     * Valida las credenciales del usuario y carga la pantalla de aeropuertos si son correctas.
     *
     * @param event el evento de acción
     */
    @FXML
    void login(ActionEvent event) {
        String usuario = txt_usuario.getText();
        String password = txt_password.getText();

        ModelUsuario usuarioModel = daoUsuario.getUsuario(usuario);
        if (usuarioModel != null && usuarioModel.getPassword().equals(password)) {
            // Usuario encontrado, cargar la pantalla de lista de aeropuertos
            loadAeropuertosScreen();
        } else {
            // Usuario no encontrado, mostrar alerta y limpiar campos
            mostrarAlerta("Usuario no encontrado", "No hay un usuario así en la base de datos.");
            txt_usuario.clear();
            txt_password.clear();
        }
    }

    /**
     * Muestra una alerta con el mensaje especificado.
     *
     * @param title   el título de la alerta
     * @param message el mensaje de la alerta
     */
    private void mostrarAlerta(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Carga la pantalla de lista de aeropuertos.
     * Cambia la escena actual a la vista de aeropuertos.
     */
    private void loadAeropuertosScreen() {
        try {
            // Cargar la nueva pantalla
            Parent root = FXMLLoader.load(getClass().getResource("ListarAeropuerto.fxml")); // Asegúrate de que la ruta sea correcta
            Stage stage = (Stage) btt_login.getScene().getWindow(); // Obtener la ventana actual
            stage.setScene(new Scene(root)); // Cambiar la escena
            stage.setTitle("AVIONES - AEROPUERTOS");
            stage.show(); // Mostrar la nueva escena
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de errores al cargar la pantalla
            mostrarAlerta("Error", "No se pudo cargar la pantalla de lista de aeropuertos.");
        }
    }
}
