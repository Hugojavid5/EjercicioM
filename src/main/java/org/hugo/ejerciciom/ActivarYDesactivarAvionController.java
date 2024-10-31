package org.hugo.ejerciciom;

import Dao.DaoAeropuerto;
import Model.ModelAeropuerto;
import Model.ModelAvion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import Dao.DaoAvion;

/**
 * Controlador para gestionar la activación y desactivación de aviones.
 */
public class ActivarYDesactivarAvionController {
    @FXML
    public Button btt_guardar;
    @FXML
    private ComboBox<ModelAeropuerto> cb_aeropuertos;

    @FXML
    private ComboBox<ModelAvion> cb_aviones;

    @FXML
    private RadioButton rb_activado;

    @FXML
    private RadioButton rb_desactivado;

    @FXML
    private ToggleGroup rb_grupo;
    @FXML
    public Button btt_cancelar;


    /**
     * Inicializa el controlador, configurando los elementos de la interfaz
     * y las listas de aeropuertos disponibles.
     */
    @FXML
    private void initialize() {
        this.cb_aeropuertos.setItems(DaoAeropuerto.listaTodas());
        rb_activado.setVisible(!ListarAeropuertoController.isBorrar());
        rb_desactivado.setVisible(!ListarAeropuertoController.isBorrar());
        if (btt_guardar != null) {
            btt_guardar.setDefaultButton(true);
        }
        if (btt_cancelar != null) {
            btt_cancelar.setCancelButton(true); // Establecer el botón Cancelar como botón de tipo cancel
        }
    }

    /**
     * Actualiza el ComboBox de aviones según el aeropuerto seleccionado.
     *
     * @param event El evento de acción que se dispara al seleccionar un aeropuerto.
     */
    @FXML
    void actualizarCB(ActionEvent event) {
        this.cb_aviones.setItems(DaoAvion.listaAviones(this.cb_aeropuertos.getSelectionModel().getSelectedItem().getId()));
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
     * Guarda los cambios realizados en el avión seleccionado.
     * Muestra un diálogo de confirmación si se va a eliminar el avión,
     * o un mensaje de información si se va a activar/desactivar.
     *
     * @param event El evento de acción que se dispara al guardar.
     */
    @FXML
    void guardar(ActionEvent event) {
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setHeaderText(null);
        if (ListarAeropuertoController.isBorrar()) {
            al.setContentText("¿Estas seguro de que deseas eliminar el avion?");
            al.showAndWait();
            if (al.getResult().getButtonData().name().equals("OK_DONE")) {
                DaoAvion.delete(cb_aviones.getSelectionModel().getSelectedItem().getModelo(), cb_aviones.getSelectionModel().getSelectedItem().getIdAeropuerto());
                cb_aviones.setItems(DaoAvion.listaAviones(cb_aeropuertos.getSelectionModel().getSelectedItem().getId()));
            }
        } else {
            al.setAlertType(Alert.AlertType.INFORMATION);
            DaoAvion.update(cb_aviones.getSelectionModel().getSelectedItem().getModelo(), cb_aviones.getSelectionModel().getSelectedItem().getIdAeropuerto(),
                    rb_activado.isSelected());
            al.setContentText("Avion modificado correctamente");
            al.showAndWait();
        }
    }
}
