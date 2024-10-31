package org.hugo.ejerciciom;

import Dao.DaoAeropuerto;
import Dao.DaoAeropuertoPrivado;
import Dao.DaoAeropuertoPublico;
import Dao.DaoDireccion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import Model.ModelAeropuertoPrivado;
import Model.ModelAeropuertoPublico;
import Model.ModelDireccion;
import javafx.stage.Stage;
import Model.ModelAeropuerto;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javax.sql.rowset.serial.SerialBlob;

import java.net.URL;
import java.util.ResourceBundle;




/**
 * Controlador para la gestión de la adición y edición de aeropuertos en la aplicación.
 * Este controlador maneja la entrada del usuario a través de un formulario para crear
 * o modificar aeropuertos, tanto públicos como privados.
 */
public class AniadirYEditarAeropuertoController implements Initializable {

    @FXML public Button btt_guardar;
    @FXML
    private Label lbl_Financiacion;
    @FXML
    private Label lbl_NumeroDeSocios;
    @FXML
    private Label lbL_NumeroDeTrabajadores;
    @FXML
    private RadioButton rb_privado;
    @FXML
    private RadioButton rb_publico;
    @FXML
    private Blob imagenBlob;


    public RadioButton getRbPublico() {
        return rb_publico;
    }

    public RadioButton getRbPrivado() {
        return rb_privado;
    }

    @FXML
    private ToggleGroup rb_tipoAeropuerto;
    @FXML
    private TextField txt_anioDeInauguracion;
    @FXML
    private TextField txt_calle;
    @FXML
    private TextField txt_capacidad;
    @FXML
    private TextField txt_ciudad;
    @FXML
    private TextField txt_financiacion;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_numero;
    @FXML
    private TextField txt_pais;
    @FXML
    private TextField txt_trabajadores;
    @FXML
    private TextField txt_numeroDeSocios;
    @FXML
    public Button btt_cancelar;

    private TableView<ModelAeropuertoPrivado> tablaPrivado;
    private TableView<ModelAeropuertoPublico> tablaPublico;
    @FXML
    void generarCampos(ActionEvent event) {
        if (rb_privado.isSelected()) {
            // Mostrar campos para aeropuerto privado
            lbl_NumeroDeSocios.setVisible(true);
            txt_numeroDeSocios.setVisible(true);

            // Ocultar campos para aeropuerto público
            lbl_Financiacion.setVisible(false);
            txt_financiacion.setVisible(false);
            lbL_NumeroDeTrabajadores.setVisible(false);
            txt_trabajadores.setVisible(false);
        } else if (rb_publico.isSelected()) {
            // Mostrar campos para aeropuerto público
            lbl_Financiacion.setVisible(true);
            txt_financiacion.setVisible(true);
            lbL_NumeroDeTrabajadores.setVisible(true);
            txt_trabajadores.setVisible(true);

            // Ocultar campos para aeropuerto privado
            lbl_NumeroDeSocios.setVisible(false);
            txt_numeroDeSocios.setVisible(false);
        }
    }

    /**
     * Establece el texto del campo de año de inauguración.
     * @param anio El año a establecer en el campo.
     */
    public void setTxtAnioInauguracionText(String anio) {
        txt_anioDeInauguracion.setText(anio);
    }

    /**
     * Establece el texto del campo de calle.
     * @param calle La calle a establecer en el campo.
     */
    public void setTxtCalleText(String calle) {
        txt_calle.setText(calle);
    }

    /**
     * Establece el texto del campo de capacidad.
     * @param capacidad La capacidad a establecer en el campo.
     */
    public void setTxtCapacidadText(String capacidad) {
        txt_capacidad.setText(capacidad);
    }

    /**
     * Establece el texto del campo de ciudad.
     * @param ciudad La ciudad a establecer en el campo.
     */
    public void setTxtCiudadText(String ciudad) {
        txt_ciudad.setText(ciudad);
    }

    /**
     * Establece el texto del campo de financiación.
     * @param financiacion La financiación a establecer en el campo.
     */
    public void setTxtFinanciacionText(String financiacion) {
        txt_financiacion.setText(financiacion);
    }

    /**
     * Establece el texto del campo de nombre.
     * @param nombre El nombre a establecer en el campo.
     */
    public void setTxtNombreText(String nombre) {
        txt_nombre.setText(nombre);
    }

    /**
     * Establece el texto del campo de número.
     * @param numero El número a establecer en el campo.
     */
    public void setTxtNumeroText(String numero) {
        txt_numero.setText(numero);
    }

    /**
     * Establece el texto del campo de número de trabajadores.
     * @param numTrabajadores El número de trabajadores a establecer en el campo.
     */
    public void setTxtNumTrabajadoresText(String numTrabajadores) {
        txt_trabajadores.setText(numTrabajadores);
    }

    /**
     * Establece el texto del campo de país.
     * @param pais El país a establecer en el campo.
     */
    public void setTxtPaisText(String pais) {
        txt_pais.setText(pais);
    }

    /**
     * Establece el texto del campo de número de socios.
     * @param numSocios El número de socios a establecer en el campo.
     */
    public void setTxtNumSociosText(String numSocios) {
        txt_numeroDeSocios.setText(numSocios);
    }

    /**
     * Establece la tabla de aeropuertos privados.
     * @param tablaPrivado La tabla de aeropuertos privados.
     */
    public void setTablaPrivado(TableView<ModelAeropuertoPrivado> tablaPrivado) {
        this.tablaPrivado = tablaPrivado;
    }

    /**
     * Establece la tabla de aeropuertos públicos.
     * @param tablaPublico La tabla de aeropuertos públicos.
     */
    public void setTablaPublico(TableView<ModelAeropuertoPublico> tablaPublico) {
        this.tablaPublico = tablaPublico;
    }

    /**
     * Cancela la acción actual y cierra el formulario.
     * @param event El evento de acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Guarda los datos del aeropuerto, ya sea añadiendo un nuevo aeropuerto o modificando uno existente.
     * @param event El evento de acción.
     */
    @FXML
    void guardar(ActionEvent event) {
        String error="";
        String nombre=txt_nombre.getText();
        String pais=txt_pais.getText();
        String ciudad=txt_ciudad.getText();
        String calle=txt_calle.getText();
        int numero=-1;
        int anioInauguracion=-1;
        int capacidad=-1;
        boolean esPublico=rb_publico.isSelected();
        float financiacion=-1;
        int numTrabajadores=-1;
        int numSocios=-1;
        boolean existe=false;
        //Validacion
        error = validarStrings(error);
        if(txt_numero.getText().isEmpty()) {
            error+="El campo numero es obligatorio\n";
        }else {
            try {
                numero=Integer.parseInt(txt_numero.getText());
                if(numero<=0) {
                    throw new Exception();
                }
            }catch(NumberFormatException e) {
                error+="El numero de la calle debe ser un numero\n";
            } catch (Exception e) {
                error+="El numero de la calle no puede ser menor que 1\n";
            }
        }
        if(txt_anioDeInauguracion.getText().isEmpty()) {
            error+="El campo año de inauguracion es obligatorio\n";
        }else {
            try {
                anioInauguracion=Integer.parseInt(txt_anioDeInauguracion.getText());
                if(anioInauguracion<1900) {
                    throw new Exception();
                }
            }catch(NumberFormatException e) {
                error+="El año de inauguracion debe ser un numero\n";
            } catch (Exception e) {
                error+="El año de inauguracion no puede ser menor al 1900\n";
            }
        }
        if(txt_capacidad.getText().isEmpty()) {
            error+="El campo capacidad es obligatorio\n";
        }else {
            try {
                capacidad=Integer.parseInt(txt_capacidad.getText());
                if(capacidad<=0) {
                    throw new Exception();
                }
            }catch(NumberFormatException e) {
                error+="La capacidad debe ser un numero\n";
            } catch (Exception e) {
                error+="La capacidad no puede ser menor a 1\n";
            }
        }
        if(esPublico) {
            if(esPublico&&txt_financiacion.getText().isEmpty()) {
                error+="El campo financiacion es obligatorio\n";
            }else {
                if(!txt_financiacion.getText().matches("^\\d{1,10}(\\.\\d{1,2})?$")){
                    error+="La financiacion puede tener como mucho 10 digitos antes del punto y 2 despues\n";
                }else {
                    try {
                        financiacion=Float.parseFloat(txt_financiacion.getText());
                        if(financiacion<=0) {
                            throw new Exception();
                        }
                    }catch(NumberFormatException e) {
                        error+="La financiacion debe ser un numero\n";
                    } catch (Exception e) {
                        error+="La financiacion no puede ser menor que 1\n";
                    }
                }
            }
        }
        if(esPublico) {
            if(txt_trabajadores.getText().isEmpty()) {
                error+="El campo numero de trabajadores es obligatorio\n";
            }else {
                try {
                    numTrabajadores=Integer.parseInt(txt_trabajadores.getText());
                    if(numTrabajadores<=0) {
                        throw new Exception();
                    }
                }catch(NumberFormatException e) {
                    error+="El numero de trabajadores debe ser un numero\n";
                } catch (Exception e) {
                    error+="El numero de trabajadores no puede ser menor que 1\n";
                }
            }
        }
        if(!esPublico) {
            if(txt_numeroDeSocios.getText().isEmpty()) {
                error+="El campo Nº socios es obligatorio\n";
            }else {
                try {
                    numSocios=Integer.parseInt(txt_numeroDeSocios.getText());
                    if(numSocios<=0) {
                        throw new Exception();
                    }
                }catch(NumberFormatException e) {
                    error+="El numero de socios debe ser un numero\n";
                } catch (Exception e) {
                    error+="El numero de socios no puede ser menor que 1\n";
                }
            }
        }
        //Una vez validado
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setHeaderText(null);
        // En el lugar donde llamas a aniadirAeropuerto
        if(ListarAeropuertoController.isEsAniadir()) {
            aniadirAeropuerto(error, nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                    financiacion, numTrabajadores, numSocios, existe, al);
        }else {
            modificarAeropuerto(error, nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                    financiacion, numTrabajadores, numSocios, existe, al);
        }
        al.showAndWait();
        tablaPrivado.getSelectionModel().clearSelection();
        tablaPublico.getSelectionModel().clearSelection();
        ListarAeropuertoController.getS().close();
    }
    void aniadirAeropuerto(String error, String nombre, String pais, String ciudad, String calle, int numero,
                           int anioInauguracion, int capacidad, boolean esPublico, float financiacion, int numTrabajadores,
                           int numSocios, boolean existe, Alert al) {
        existe = validarExistencia(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                financiacion, numTrabajadores, numSocios, existe);
        if(error.equals("")&&!existe) {
            Integer idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
            if(idDireccion==null) {
                DaoDireccion.aniadir(pais, ciudad, calle, numero);
                idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
            }
            Integer idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad, idDireccion, null);
            if(idAeropuerto==null) {
                DaoAeropuerto.aniadir(nombre, anioInauguracion, capacidad, idDireccion, null);
                idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad,idDireccion, null);
            }
            if(esPublico) {
                DaoAeropuertoPublico.aniadir(idAeropuerto, financiacion, numTrabajadores);
                ListarAeropuertoController.setListaTodasPublico(DaoAeropuertoPublico.cargarListaAeropuertosPublicos());
                tablaPublico.refresh();
            }else {
                DaoAeropuertoPrivado.aniadir(idAeropuerto, numSocios);
                ListarAeropuertoController.setListaTodasPrivado(DaoAeropuertoPrivado.cargarListaAeropuertosPrivados());
                tablaPrivado.refresh();
            }
            al.setContentText("Aeropuerto añadido correctamente");
        }else {
            if(error.equals("")) {
                al.setAlertType(AlertType.WARNING);
                error="La persona ya estaba en la lista";
            }else {
                al.setAlertType(AlertType.ERROR);
            }
            al.setContentText(error);
        }
    }

    /**
     * Valida si un aeropuerto ya existe en la lista de aeropuertos.
     *
     * Este método comprueba si un aeropuerto, dado sus atributos, ya está presente
     * en la lista de aeropuertos públicos o privados. Se crea una instancia del aeropuerto
     * según el tipo (público o privado) y se compara con los existentes en las listas correspondientes.
     *
     * @param nombre            El nombre del aeropuerto.
     * @param pais              El país del aeropuerto.
     * @param ciudad            La ciudad del aeropuerto.
     * @param calle             La calle del aeropuerto.
     * @param numero            El número de la calle del aeropuerto.
     * @param anioInauguracion  El año en que se inauguró el aeropuerto.
     * @param capacidad         La capacidad del aeropuerto.
     * @param esPublico         Un booleano que indica si el aeropuerto es público.
     * @param financiacion      La cantidad de financiación para aeropuertos públicos.
     * @param numTrabajadores   El número de trabajadores para aeropuertos públicos.
     * @param numSocios        El número de socios para aeropuertos privados.
     * @param existe            Un booleano que indica si el aeropuerto ya existe (se pasa por referencia).
     * @return true si el aeropuerto ya existe en la lista; false en caso contrario.
     */
    boolean validarExistencia(String nombre, String pais, String ciudad, String calle, int numero, int anioInauguracion,
                              int capacidad, boolean esPublico, float financiacion, int numTrabajadores, int numSocios, boolean existe) {
        if(esPublico) {
            ModelAeropuertoPublico aeropuerto=new ModelAeropuertoPublico(nombre, anioInauguracion, capacidad, new ModelDireccion(pais, ciudad, calle, numero), null, financiacion, numTrabajadores);
            for(ModelAeropuertoPublico airport:ListarAeropuertoController.getListaTodasPublico()) {
                if(airport.equals(aeropuerto)) {
                    existe=true;
                }
            }
        }else {
            ModelAeropuertoPrivado aeropuerto=new ModelAeropuertoPrivado(nombre, anioInauguracion, capacidad, new ModelDireccion(pais, ciudad, calle, numero), null, numSocios);
            for(ModelAeropuertoPrivado airport:ListarAeropuertoController.getListaTodasPrivado()) {
                if(airport.equals(aeropuerto)) {
                    existe=true;
                }
            }
        }
        return existe;
    }

    /**


        /**
         * Valida los campos de texto para asegurar que no estén vacíos.
         * @param error Mensaje de error acumulado.
         * @return Mensaje de error actualizado.
         */
    private String validarStrings(String error) {
        if (txt_nombre.getText().isEmpty()) {
            error += "El campo nombre es obligatorio\n";
        }
        if (txt_pais.getText().isEmpty()) {
            error += "El campo país es obligatorio\n";
        }
        if (txt_ciudad.getText().isEmpty()) {
            error += "El campo ciudad es obligatorio\n";
        }
        return error;
    }
    void modificarAeropuerto(String error, String nombre, String pais, String ciudad, String calle, int numero,
                             int anioInauguracion, int capacidad, boolean esPublico, float financiacion, int numTrabajadores,
                             int numSocios, boolean existe, Alert al) {
        existe = validarExistencia(nombre, pais, ciudad, calle, numero, anioInauguracion, capacidad, esPublico,
                financiacion, numTrabajadores, numSocios, existe);
        if(!existe&&error.equals("")) {
            Integer idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
            if(idDireccion==null) {
                DaoDireccion.aniadir(pais, ciudad, calle, numero);
                idDireccion=DaoDireccion.conseguirID(pais, ciudad, calle, numero);
            }
            Integer idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad, idDireccion, null);
            if(idAeropuerto==null) {
                if(esPublico) {
                    DaoAeropuerto.modificarPorId(tablaPublico.getSelectionModel().getSelectedItem().getId(), nombre, anioInauguracion, capacidad, idDireccion, imagenBlob);
                }else {
                    DaoAeropuerto.modificarPorId(tablaPrivado.getSelectionModel().getSelectedItem().getId(), nombre, anioInauguracion, capacidad, idDireccion, imagenBlob);
                }
                idAeropuerto=DaoAeropuerto.conseguirID(nombre, anioInauguracion, capacidad,idDireccion, null);
            }
            if(esPublico) {
                DaoAeropuertoPublico.modificarPorID(idAeropuerto, financiacion, numTrabajadores);
                ListarAeropuertoController.setListaTodasPublico(DaoAeropuertoPublico.cargarListaAeropuertosPublicos());
                tablaPublico.refresh();
            }else {
                DaoAeropuertoPrivado.modificarPorID(idAeropuerto, numSocios);
                ListarAeropuertoController.setListaTodasPrivado(DaoAeropuertoPrivado.cargarListaAeropuertosPrivados());
                tablaPrivado.refresh();
            }
            al.setContentText("Aeropuerto modificado correctamente");
        }else {
            if(error.equals("")) {
                al.setAlertType(AlertType.WARNING);
                error="La persona ya estaba en la lista";
            }else {
                al.setAlertType(AlertType.ERROR);
            }
            al.setContentText(error);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (btt_guardar != null) {
            btt_guardar.setDefaultButton(true);
        }
        if (btt_cancelar != null) {
            btt_cancelar.setCancelButton(true);
        }
    }

    @FXML
    void cargarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen del Aeropuerto");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null); // Cambia null por la ventana principal si es necesario
        if (file != null) {
            try (FileInputStream fis = new FileInputStream(file)) {
                // Leer todos los bytes del InputStream y crear un Blob
                byte[] imageBytes = fis.readAllBytes();
                imagenBlob = new SerialBlob(imageBytes); // Asignar el Blob a la variable local
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Imagen cargada correctamente");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                // Manejo de errores
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al cargar la imagen");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de errores para la base de datos
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error al crear el Blob");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
}
