package org.hugo.ejerciciom;

import javafx.scene.control.*;
import Dao.DaoAvion;
import Dao.DaoAeropuerto;
import Model.ModelAeropuerto;
import Model.ModelAvion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controlador para gestionar la adición de aviones a un aeropuerto.
 */
public class AniadirAvionController implements Initializable {
    @FXML
    public Button btt_guardar;
    @FXML
    private ComboBox<ModelAeropuerto> cb_aeropuerto;

    @FXML
    private RadioButton rb_activado;

    @FXML
    private RadioButton rb_desactivado;

    @FXML
    private ToggleGroup rb_grupo;

    @FXML
    private TextField txt_asientos;

    @FXML
    private TextField txt_modelo;

    @FXML
    private TextField txt_velMaxima;

    @FXML
    public Button btt_cancelar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (btt_guardar != null) {
            btt_guardar.setDefaultButton(true);
        }
        if (btt_cancelar != null) {
            btt_cancelar.setCancelButton(true);
        }
    }
    /**
     * Cancela la acción actual y cierra la ventana.
     *
     * @param event El evento de acción que se dispara al cancelar.
     */
    @FXML
    void cancelar(ActionEvent event) {
        ListarAeropuertoController.getS().close();
    }

    /**
     * Guarda los datos del avión introducidos por el usuario.
     * Valida la entrada y muestra un mensaje de información,
     * advertencia o error según el resultado de la validación.
     *
     * @param event El evento de acción que se dispara al guardar.
     */
    @FXML
    void guardar(ActionEvent event) {
        String error = "";
        String modelo = txt_modelo.getText();
        int numAsientos = -1;
        int velMax = -1;
        boolean existe = false;
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setHeaderText(null);

        if (txt_modelo.getText().isEmpty()) {
            error += "El campo modelo es obligatorio\n";
        }
        if (txt_asientos.getText().isEmpty()) {
            error += "El campo asientos es obligatorio\n";
        } else {
            try {
                numAsientos = Integer.parseInt(txt_asientos.getText());
                if (numAsientos <= 0) {
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                error += "El numero de asientos debe ser un numero entero\n";
            } catch (Exception e) {
                error += "El numero de asientos debe ser mayor que 0\n";
            }
        }
        if (txt_velMaxima.getText().isEmpty()) {
            error += "La velocidad maxima es obligatoria\n";
        } else {
            try {
                velMax = Integer.parseInt(txt_velMaxima.getText());
                if (velMax < 1) {
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                error += "La velocidad maxima debe ser un numero entero\n";
            } catch (Exception e) {
                error += "La velocidad maxima debe ser mayor que 0\n";
            }
        }
        for (ModelAvion avion : DaoAvion.conseguirListaTodos()) {
            if (avion.getModelo().equals(modelo) && cb_aeropuerto.getSelectionModel().getSelectedItem().getId() == avion.getIdAeropuerto()) {
                existe = true;
            }
        }
        if (error.equals("") && !existe) {
            DaoAvion.aniadir(modelo, numAsientos, velMax, rb_activado.isSelected(), cb_aeropuerto.getSelectionModel().getSelectedItem().getId());
            al.setContentText("Avion añadido correctamente");
        } else {
            if (error.equals("")) {
                al.setAlertType(Alert.AlertType.WARNING);
                al.setContentText("Ya existe un avion de ese modelo en ese aeropuerto");
            } else {
                al.setAlertType(Alert.AlertType.ERROR);
                al.setContentText(error);
            }
        }
        al.showAndWait();
    }

    /**
     * Inicializa el controlador, configurando los elementos de la interfaz
     * y las listas de aeropuertos disponibles.
     */
    @FXML
    private void initialize() {
        this.cb_aeropuerto.setItems(DaoAeropuerto.listaTodas());
    }
}
